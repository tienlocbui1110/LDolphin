var express = require('express');
var router = express.Router();

var imageController = require('../controllers/imageController');

/* GET users listing. */
router.get('/', function(req, res) {
    imageController.getAll(function(images) {
        images = images;
    });
});

router.get('/:id', function(req, res) {
    let id = req.params.id;
    let output = [];
    imageController.getById(id, function(image) {
        output.push(image);
    });
    imageController.getByUserId(id, function(images) {
        for (let i = 0; i < images.length; i++) {
            output.push(images[i]);
        }
    });
    return output;
});


router.post('/', function(req, res) {
    let info = {
        'imageType': req.body.imageType,
        'userId': req.body.userId,
        'status': req.body.status,
        'linkLargeImage': req.body.linkLargeImage,
        'linkThubnailImage': req.body.linkThubnailImage,
        'name': req.body.name,
        'title': req.body.title,
        'tag': req.body.tag,
        'size': req.body.size,
        'shortLink': req.body.shortLink,
    };

    imageController.upImage(info);
});

module.exports = router;