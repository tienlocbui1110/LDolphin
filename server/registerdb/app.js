var express = require('express');
var mongoose = require('mongoose');
var bodyParser = require("body-parser");
var cookieParser = require('cookie-parser');
var LocalStrategy = require("passport-local");
var User = require("./models/user")

var app = express();

// Middleware
app.use(bodyParser.json());       // to support JSON-encoded bodies
app.use(bodyParser.urlencoded({     // to support URL-encoded bodies
    extended: true
}));
app.use(express.json());       // to support JSON-encoded bodies
app.use(express.urlencoded()); // to support URL-encoded bodies
app.use(cookieParser());

//passport
var passport = require('passport');
var session = require('express-session');

const expressSession = require('express-session');
app.use(expressSession({
    secret: 'mySecretKey',
    resave: false,
    saveUninitialized: false
}));

app.use(passport.initialize());
app.use(passport.session()); // persistent login sessions

passport.use(new LocalStrategy({
    usernameField: 'email'
}, User.authenticate()));
passport.serializeUser(User.serializeUser());
passport.deserializeUser(User.deserializeUser());

mongoose.connect('mongodb://localhost/RegisterDB', { useNewUrlParser: true })
    .then(() => { console.log('MongoDB connected...') })
    .catch(err => console.log(err));


app.post('/signup', function(req, res) {
    res.contentType('application/json');
    res.setHeader("Access-Control-Allow-Origin", "*");

    User.register(new User({
        email: req.body.email,
        name: req.body.name
    }), req.body.password, function(err) {
        if (err) {
            res.status(401);
            res.json({
                "status": "error",
                "message": err
            });
        }
        passport.authenticate("local")(req, res, function() {
            success(req,res);
        });
    });
});

app.get("/login", function(req, res) {
    if (req.isAuthenticated()) {
       success(req,res);
    } else {
       fail(req,res);
    }
});

function fail(req, res) {
    res.contentType('application/json');
    res.setHeader("Access-Control-Allow-Origin", "*");
    res.status(401);
    res.json({
        "status": "error",
        "message": "Unauthorized"
    });
}

function success(req, res) {
    res.contentType('application/json');
    res.setHeader("Access-Control-Allow-Origin", "*");
    res.status(200);
    res.json({
        "status": "ok",
        "message": "Successfully",
        "data": {
            userId : req.user._id,
            email: req.user.email,
            name: req.user.name
        }
    });
}

app.post("/login", passport.authenticate("local"),function(req,res){
    success(req,res);
});

function isLoggedIn(req, res, next) {
    if (req.isAuthenticated()) {
        return next();
    }
    fail(req,res);
}



var Config = require("../config")

app.listen(Config.register.port);