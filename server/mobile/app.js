var createError = require('http-errors');
var express = require('express');
var mongoose = require('mongoose');
var bodyParser = require("body-parser");
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');
var LocalStrategy = require("passport-local");
var User = require('./models/user');


var indexRouter = require('./routes/index');
var usersRouter = require('./routes/users');
var imageRouter = require('./routes/image')

var app = express();

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'hbs');

app.use(logger('dev'));
app.use(express.json());
app.use(bodyParser.urlencoded({ extended: true }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

//passport
var passport = require('passport');
var flash = require('connect-flash');
var session = require('express-session');

const expressSession = require('express-session');
app.use(expressSession({
    secret: 'mySecretKey',
    resave: false,
    saveUninitialized: false
}));

//require('./config/passport.js');

app.use(passport.initialize());
app.use(passport.session()); // persistent login sessions
// app.use(flash());

passport.use(new LocalStrategy({
    usernameField: 'email'
}, User.authenticate()));
passport.serializeUser(User.serializeUser());
passport.deserializeUser(User.deserializeUser());

mongoose.connect('mongodb://localhost:27017/lDophin');


// app.post('/signup', passport.authenticate('local-signup', {
//     successRedirect: '/userPage',
//     failureRedirect: '/',
//     failureFlash: true
// }));

// app.post('/signin', passport.authenticate('local-signin', {
//     successRedirect: '/userPage',
//     failureRedirect: '/'
// }));

app.post('/signup', function(req, res) {
    User.register(new User({
        email: req.body.email,
        Name: req.body.name,
        CMND: req.body.CMND,
        phoneNumber: req.body.phoneNumber
    }), req.body.password, function(err) {
        if (err) {
            console.log(err);
            return res.send("failed");
        }
        passport.authenticate("local")(req, res, function() {
            console.log("Successful");
            res.redirect('secret');
        });
    });
});

app.get("/login", function(req, res) {
    if (req.isAuthenticated()) {
        res.redirect("secret");
    } else {
        res.render("login");
    }
});

app.post("/login", passport.authenticate("local", {
    successRedirect: "/secret",
    failureRedirect: "/login"
}), function(req, res) {

});

app.get("/secret", isLoggedIn, function(req, res) {
    console.log("ID: " + req.user._id.toString());
    res.send("sescret");
});

function isLoggedIn(req, res, next) {
    if (req.isAuthenticated()) {
        return next();
    }
    res.redirect('/');
}

app.use('/', indexRouter);
app.use('/users', usersRouter);
app.use('/image', imageRouter);

// catch 404 and forward to error handler
app.use(function(req, res, next) {
    next(createError(404));
});

// error handler
app.use(function(err, req, res, next) {
    // set locals, only providing error in development
    res.locals.message = err.message;
    res.locals.error = req.app.get('env') === 'development' ? err : {};

    // render the error page
    res.status(err.status || 500);
    res.render('error');
});

app.set('port', (process.env.PORT || 5000));

app.listen(app.get('port'), function() {
    console.log('Server is listening at port ' + app.get('port'));
});


module.exports = app;