var express = require('express');
var router = express.Router();
var Image = require('../models/image');

router.get('/', function(req, res, next) {
    Image.find({}, function(err, images) {

        var page = 1
        var rowInPage = 10
        if (req.param('p')) page = req.param('p')
        var pageCount = Math.round(images.length / rowInPage + 0.5)
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

router.get('/getImageByTitle/:title', function(req, res, next) {
    var title = req.params.title;
    Image.find({
        "title": title
    }, function(err, images) {

        var page = 1
        var rowInPage = 10
        if (req.param('p')) page = req.param('p')
        var pageCount = Math.round(images.length / rowInPage + 0.5)
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

router.get('/getImageByUserId/:userId', function(req, res, next) {
    var id = req.params.userId;
    Image.find({
        userId: id
    }, function(err, images) {

        var page = 1
        var rowInPage = 10
        if (req.param('p')) page = req.param('p')
        var pageCount = Math.round(images.length / rowInPage + 0.5)
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

router.post('/:userId/addImage', function(req, res, next) {
    var id = req.params.userId;
    var image = req.body.image;
    image.userId = id;
    Image.insertOne(image);
    res.send('Add a new image')
})

router.get('/:id/updateImage/liked', function(req, res, next) {
    var id = req.params.id;
    Image.findOneAndUpdate({
        "_id": id
    }, {
        $inc: {
            "countLike": 1
        }
    });
})

module.exports = router;