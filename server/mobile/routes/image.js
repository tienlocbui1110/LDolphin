var express = require('express');
var router = express.Router();
var Image = require('../models/image');

router.get('/', function(req, res, next) {
    var images = Image.find();
    Image.find({}, function(err, images) {
        var imageMap = {};

        images.forEach(function(image) {
            imageMap[image._id] = image;
        });

        console.log(images);
        res.send(imageMap);
    });
})

router.get('/:id', function(req, res, next) {
    var id = req.params.id;
    Image.find({
        "_id": id
    }, function(err, image) {
        var imageMap = {};

        image.forEach(function(image) {
            imageMap[image._id] = image;
        });

        console.log(image);
        res.send(imageMap);
    });
})

router.get('/user/:userId', function(req, res, next) {
    var id = req.params.userId;
    Image.find({
        userId: id
    }, function(err, images) {
        var imageMap = {};

        images.forEach(function(image) {
            imageMap[image._id] = image;
        });

        console.log(images);
        res.send(imageMap);
    });
})

router.get('/:userId/addImage', function(req, res, next) {
    var id = req.params.userId;
    var image = req.body.image;
    image.userId = id;
    Image.insertOne(image);
})

router.get('/:id/updateImage/liked', function(req, res, next) {
    var id = req.params.id;
    Image.findOneAndUpdate({ "_id": id }, { $inc: { "countLike": 1 } });
})

router.get('/:id/updateImage/comment', function(req, res, next) {
    var id = req.params.id;
    Image.findOneAndUpdate({ "_id": id }, { $inc: { "countComment": 1 } });
})

module.exports = router;