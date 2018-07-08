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
})

router.get('/getUserByName/:name', function(req, res, next) {
    var name = req.params.name;
    User.find({ "Name": name }, function(err, users) {

        var page = 1
        var rowInPage = 10
        if (req.param('p')) page = req.param('p')
        var pageCount = Math.round(users.length / rowInPage + 0.5)
        var pagination = {
            page: page,
            pageCount: pageCount
        }
        users = users.slice((pagination.page - 1) * rowInPage, pagination.page * rowInPage)

        var userMap = {};

        users.forEach(function(user) {
            userMap[user._id] = user;
        });

        console.log(users);
        res.send(userMap);
    });
})


router.get('/getUserByEmail/:email', function(req, res, next) {
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

router.post('/addUser', function(req, res, next) {
    var user = req.body.user;
    User.insertOne(user);
    res.send('Add a new user')
})

router.get('/:id/imageStored', function(req, res, next) {
    var id = req.params.id;
    userStore.find({ userId: id }, function(err, images) {

        var page = 1
        var rowInPage = 10
        if (req.param('p')) page = req.param('p')
        var pageCount = Math.round(products.length / rowInPage + 0.5)
        var pagination = {
            page: page,
            pageCount: pageCount
        }
        images = images.slice((pagination.page - 1) * rowInPage, pagination.page * rowInPage)

        var imageMap = {};

        images.forEach(function(image) {
            imageMap[image._id] = image;
        });

        console.log(images);
        res.send(imageMap);
    });
})

router.post('/:id/imageStored/add', function(req, res, next) {
    var id = req.params.id;
    var data = req.body.image;
    data.userId = id;
    userStore.insertOne(data);
    res.send('Add a new stored image')
})

router.get('/:id/imageLiked', function(req, res, next) {
    var id = req.params.id;

    likedStore.find({ userId: id }, function(err, images) {

        var page = 1
        var rowInPage = 10
        if (req.param('p')) page = req.param('p')
        var pageCount = Math.round(products.length / rowInPage + 0.5)
        var pagination = {
            page: page,
            pageCount: pageCount
        }
        images = images.slice((pagination.page - 1) * rowInPage, pagination.page * rowInPage)

        var imageMap = {};

        images.forEach(function(image) {
            imageMap[image._id] = image;
        });

        console.log(images);
        res.send(imageMap);
    });
})

router.post('/:id/imageLiked/add', function(req, res, next) {
    var id = req.params.id;
    var data = req.body.image;
    data.userId = id;
    likedStore.insertOne(data);
    res.send('Add a new liked image')
})

module.exports = router;