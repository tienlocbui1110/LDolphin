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

var multer = require('multer');
var mongoose = require('mongoose');
var fs = require('fs');

var db_filename = "demo.jpg";
var local_file = "./gridfs.jpg";

var Grid = require("gridfs-stream");
Grid.mongo = mongoose.mongo;
var connection = mongoose.connection;
connection.on('error', console.error.bind(console, 'connection error:'));
connection.once('open', function() {

    var gfs = Grid(connection.db);
    // Reading a file from MongoDB
    router.get('/:userId/read/:title', function(req, res) {
        // Check file exist on MongoDB
        var title = req.params.title;
        gfs.exist({
            filename: title
        }, function(err, file) {
            if (err || !file) {
                res.send('File Not Found');
            } else {
                var readstream = gfs.createReadStream({
                    filename: title
                });
                readstream.pipe(res);
            }
        });
    });

    // Delete a file from MongoDB
    router.get('/:userId/delete/:imageId', function(req, res) {
        var imageId = req.params.imageId;
        gfs.exist({
            _id: imageId,
        }, function(err, file) {
            if (err || !file) {
                res.send('File Not Found');
            } else {
                gfs.remove({
                    _id: imageId,
                }, function(err) {
                    if (err) res.send(err);
                    res.send('File Deleted');
                });
            }
        });
    });

    // Get file information(File Meta Data) from MongoDB
    router.get('/:userId/meta/:imageId/:title', function(req, res) {
        var title = req.params.title;
        var imageId = req.params.imageId;
        gfs.exist({
            filename: title,
        }, function(err, file) {
            if (err || !file) {
                res.send('File Not Found');
            } else {
                gfs.files.find({
                    "_id": imageId
                }, function(err, image) {
                    var imageMap = {};

                    image.forEach(function(image) {
                        imageMap[image._id] = image;
                    });

                    console.log(image);
                    res.send(imageMap);
                });
            }
        });
    });
});

var upload = multer({
    dest: "./uploads"
});

router.post('/:userId/addImage', upload.array('photos', 200), function(req, res, next) {
    var gfs = Grid(connection.db);
    var ss = req.files;
    for (var j = 0; j < ss.length; j++) {
        var originalName = ss[j].originalname;
        var filename = ss[j].filename;
        var writestream = gfs.createWriteStream({
            filename: originalName
        });
        fs.createReadStream("./uploads/" + filename).pipe(writestream);
        writestream.on('close', function(file) {
            var id = req.params.userId;
            var tag = req.params.tag;
            var image = new Image();
            image.imageLargerPath = "aa/bb";
            image.imageThumbnailPath = "aa/bb";
            image.title = file.filename;
            image.tag = tag;
            image.userId = id;
            image.Size = file.length;
            image.countLike = 0;
            image.countComment = 0;
            image.shortLink = "aa";
            image.typeOfImage = 1;
            image.imageStatus = 1;
            console.log(file);
            image.save();
            res.send(image);
        });
    }
});

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