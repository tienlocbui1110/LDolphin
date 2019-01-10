var express = require("express");
var app = express()
var bodyParser = require('body-parser')

// Connect to db
var mongoose = require('mongoose');
mongoose.connect('mongodb://localhost/coredb', { useNewUrlParser: true })
    .then(() => { console.log('MongoDB connected...') })
    .catch(err => console.log(err));

// Middleware
app.use(bodyParser.json());       // to support JSON-encoded bodies
app.use(bodyParser.urlencoded({     // to support URL-encoded bodies
    extended: true
}));
app.use(express.json());       // to support JSON-encoded bodies
app.use(express.urlencoded()); // to support URL-encoded bodies

// Use routes
app.use("/article", require("./routes/article"));
app.use("/user", require("./routes/user"));

// Listenning
var Config = require("../config");
app.listen(Config.coredb.port);