var express = require('express')
var router = express.Router()

const User = require("../models/user")
// http://localhost:3000/user ==> Create new User
router.post('/', function (req, res) {
    res.contentType('application/json');
    res.setHeader("Access-Control-Allow-Origin", "*");

    var userId = req.body.userId;
    var email = req.body.email;
    var name = req.body.name;
    
    user = new User({
        _id: userId,
        name: name,
        email: email
    });

    user.save().then(function (product) {
        // Save successfully
        res.status(200);
        res.json({
            "status": "ok",
            "message": "save successfully"
        });
    }).catch(err => {
        // Error when saving
        res.statusCode = 400;
        res.json({
            "status": "error",
            "message": err
        });
    });
})

module.exports = router