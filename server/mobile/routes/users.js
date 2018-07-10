var express = require('express');
var router = express.Router();
var User = require('../models/user');
var likedStore = require('../models/likedStore');
var userStore = require('../models/userStore');

router.get('/', function(req, res, next) {
    User.find({}, function(err, users) {
        var userMap = {};

        users.forEach(function(user) {
            userMap[user._id] = user;
        });

        console.log(users);
        res.send(userMap);
    });
})

router.get('/:id', function(req, res, next) {
    var id = req.params.id;
    User.find({ "_id": id }, function(err, user) {
        var userMap = {};

        user.forEach(function(user) {
            userMap[user._id] = user;
        });

        console.log(user);
        res.send(userMap);
    });
    console.log(user);
})

router.get('/:email', function(req, res, next) {
    var email = req.params.email;
    User.find({ "email": email }, function(err, user) {
        var userMap = {};

        user.forEach(function(user) {
            userMap[user._id] = user;
        });

        console.log(user);
        res.send(userMap);
    });
})

router.get('/addUser', function(req, res, next) {
    var user = req.body.user;
    User.insertOne(user);
    console.log(user);
    console.log("hihihehe");
})

router.get('/:id/imageStored', function(req, res, next) {
    var id = req.params.id;
    userStore.find({ userId: id }, function(err, images) {
        var imageMap = {};

        images.forEach(function(image) {
            imageMap[image._id] = image;
        });

        console.log(images);
        res.send(imageMap);
    });
})

router.get('/:id/imageStored/add', function(req, res, next) {
    var id = req.params.id;
    var data = req.body.image;
    data.userId = id;
    userStore.insertOne(data);
    console.log(images);
})

router.get('/:id/imageLiked', function(req, res, next) {
    var id = req.params.id;
    likedStore.find({ userId: id }, function(err, images) {
        var imageMap = {};

        images.forEach(function(image) {
            imageMap[image._id] = image;
        });

        console.log(images);
        res.send(imageMap);
    });
})

router.get('/:id/imageLiked/add', function(req, res, next) {
    var id = req.params.id;
    var data = req.body.image;
    data.userId = id;
    likedStore.insertOne(data);
    console.log(images);
})

module.exports = router;
