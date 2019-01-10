var express = require("express");
var app = express();
var crypto = require("crypto")
var multer = require('multer')
var path = require("path");
var Config = require("../config");

const storage = multer.diskStorage({
    destination: 'public/',
    filename: function (req, file, callback) {
        crypto.pseudoRandomBytes(16, function (err, raw) {
            if (err) return callback(err);
            callback(null, raw.toString('hex') + path.extname(file.originalname));
        });
    }
});


var upload = multer({
    storage: storage,
    fileFilter: function (req, file, callback) {
        var ext = path.extname(file.originalname);
        if (ext !== '.png' && ext !== '.jpg' && ext !== '.gif' && ext !== '.jpeg') {
            return callback('Only images are allowed')
        }
        callback(null, true)
    }
})

var uploadCallback = upload.single('image')
// Uploading
app.post("/", (req, res) => {
    res.contentType('application/json');
    res.setHeader("Access-Control-Allow-Origin", "*");
    uploadCallback(req, res, function (err) {
        if (err) {
            // An error occurred when uploading 
            res.status = 400;
            res.json({
                "status": "error",
                "message": err
            });
            return
        }
        // Everything went fine 
        if (!req.file) {
            console.log("No file received");
            res.status = 400;
            res.json({
                "status": "error",
                "message": "No file received"
            });
        } else {
            res.status = 200;
            res.json({
                "status": "ok",
                "message": "Upload successfully",
                "data": {
                    imagePath: "http://localhost:" + Config.imagedb.port + "/" + req.file.path
                }
            });
        }
    })
});

app.use(express.static('public'))

// Listenning
app.listen(Config.imagedb.port);