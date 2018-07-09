var express = require('express');
var router = express.Router();
var User = require('../models/user');
var likedStore = require('../models/likedStore');
var userStore = require('../models/userStore');

router.get('/', function(req, res, next) {
    var perPage = 10,
        page = Math.max(0, req.query.page);
    var name = req.query.name;
    var email = req.query.email;

    if (req.isAuthenticated() == true) {
        if (name != null) {
            User.find({
                    "Name": name
                }).limit(perPage)
                .skip(perPage * page)
                .sort({
                    Name: 'asc'
                }).exec(function(err, users) {
                    console.log(users);
                    res.send(JSON.stringify(users));
                });
        } else if (email != null) {
            User.find({
                "email": email
            }, function(err, user) {
                console.log(user);
                res.send(JSON.stringify(user));
            });
        } else {
            User.find({}).limit(perPage)
                .skip(perPage * page)
                .sort({
                    Name: 'asc'
                }).exec(function(err, users) {
                    console.log(users);
                    res.send(JSON.stringify(users));
                });
        }
    } else {
        res.status(401);
    }
})

router.get('/:id', function(req, res, next) {
    var id = req.params.id;
    if (req.isAuthenticated() == true) {
        User.find({
            "_id": id
        }, function(err, user) {
            console.log(user);
            res.send(JSON.stringify(user));
        });
    } else {
        res.status(401);
    }

})

router.get('/:id/imageStored/:page', function(req, res, next) {
    var id = req.params.id;
    var perPage = 10,
        page = Math.max(0, req.params.page);
    if (req.isAuthenticated() == true) {
        userStore.find({
                userId: id
            }).limit(perPage)
            .skip(perPage * page)
            .exec(function(err, images) {
                console.log(images);
                res.send(JSON.stringify(images));
            });
    } else {
        res.status(401);
    }
})

router.post('/:id/imageStored/add', function(req, res, next) {
    var id = req.params.id;
    var data = new userStore();
    data = req.body.image;
    data.userId = id;
    data.save();
    res.send('Add a new stored image')
})

router.get('/:id/imageLiked/:page', function(req, res, next) {
    var id = req.params.id;
    var perPage = 10,
        page = Math.max(0, req.params.page);
    if (req.isAuthenticated() == true) {
        likedStore.find({
                userId: id
            }).limit(perPage)
            .skip(perPage * page)
            .exec(function(err, images) {
                console.log(images);
                res.send(JSON.stringify(images));
            });
    } else {
        res.status(401);
    }
})

router.post('/:id/imageLiked/add', function(req, res, next) {
    var id = req.params.id;
    var data = new likedStore();
    data = req.body.image;
    data.userId = id;
    data.save();
    res.send('Add a new liked image')
})

module.exports = router;