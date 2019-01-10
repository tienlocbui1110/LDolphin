var express = require("express");
var app = express()
var bodyParser = require('body-parser')
var axious = require("axios");

// Middleware
app.use(bodyParser.json());       // to support JSON-encoded bodies
app.use(bodyParser.urlencoded({     // to support URL-encoded bodies
    extended: true
}));
app.use(express.json());       // to support JSON-encoded bodies
app.use(express.urlencoded()); // to support URL-encoded bodies

// Use routes

// ----- LOGIN ----------- //
app.post("/login", (req,res)=>{
  // PROXY ?
});
// ----- SIGNUP ---------- //
app.post("/signup", (req,res)=>{

});
// ----- GET ALL ARTICLES --- //

// ----- GET USER ARTICLE --- //

// ----- UPLOAD ------------- //

// ----- Add New post ------- //

// ----- DELETE Article ----- //

// Listenning
var Config = require("../config");
app.listen(Config.api.port);