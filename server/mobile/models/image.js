var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var schema = new Schema({
    imageLargerPath: {
        type: String,
        required: true
    },
    imageThumbnailPath: {
        type: String
    },
    title: {
        type: String,
        required: true
    },
    tag: {
        type: String
    },
    Size: {
        type: String
    },
    countLike: {
        type: Number
    },
    countComment: {
        type: Number
    },
    shortLink: {
        type: String
    },
    typeOfImage: {
        type: Number
    },
    imageStatus: {
        type: Number
    },
    userId: {
        type: String
    }
});

module.exports = mongoose.model('Image', schema);