var express = require('express');
var router = express.Router();
var Image = require('../models/image');

router.get('/', function(req, res, next) {
    var perPage = 10,
        page = Math.max(0, req.query.page);
    var title = req.query.title;
    var userId = req.query.userId;

    if (req.isAuthenticated() == true) {
        if (title != null) {
            Image.find({
                    "title": title
                }).limit(perPage)
                .skip(perPage * page)
                .sort({
                    title: 'asc'
                }).exec(function(err, images) {
                    console.log(images);
                    res.send(JSON.stringify(images));
                });
        } else if (userId != null) {
            Image.find({
                    "userId": userId
                }).limit(perPage)
                .skip(perPage * page)
                .sort({
                    title: 'asc'
                }).exec(function(err, images) {
                    console.log(images);
                    res.send(JSON.stringify(images));
                });
        } else {
            Image.find({}).limit(perPage)
                .skip(perPage * page)
                .sort({
                    title: 'asc'
                }).exec(function(err, images) {
                    console.log(images);
                    res.send(JSON.stringify(images));
                });
        }
    } else {
        res.status(401);
    }
})

router.get('/:id', function(req, res, next) {
    var id = req.params.id;
    if (req.isAuthenticated() == true) {
        Image.find({
            "_id": id
        }, function(err, image) {
            console.log(image);
            res.send(image);
        });
    } else {
        res.status(401);
    }
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
        if (req.isAuthenticated() == true) {
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
        } else {
            res.status(401);
        }

    });

    // Delete a file from MongoDB
    router.delete('/:userId/delete/:imageId', function(req, res) {
        var imageId = req.params.imageId;
        if (req.isAuthenticated() == true) {
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
        } else {
            res.status(401);
        }

    });

    // Get file information(File Meta Data) from MongoDB
    router.get('/:userId/meta/:imageId/:title', function(req, res) {
        var title = req.params.title;
        var imageId = req.params.imageId;
        if (req.isAuthenticated() == true) {
            gfs.exist({
                filename: title,
            }, function(err, file) {
                if (err || !file) {
                    res.send('File Not Found');
                } else {
                    gfs.files.find({
                        "_id": imageId
                    }, function(err, image) {
                        console.log(image);
                        res.send(imageMap);
                    });
                }
            });
        } else {
            res.status(401);
        }

    });
});

var upload = multer({
    dest: "./uploads"
});

router.post('/:userId', upload.array('photos', 200), function(req, res, next) {
    if (req.isAuthenticated() == true) {
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
    } else {
        res.status(401);
    }

});

router.get('/:id/updateImage/liked', function(req, res, next) {
    var id = req.params.id;
    if (req.isAuthenticated() == true) {
        Image.findOneAndUpdate({
            "_id": id
        }, {
            $inc: {
                "countLike": 1
            }
        });
        res.send("thêm dc r");
    } else {
        res.status(401);
    }

})

module.exports = router;