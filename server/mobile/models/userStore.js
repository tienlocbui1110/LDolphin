var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var schema = new Schema({
    imagePath: {
        type: String,
        required: true
    },
    userId: {
        type: Number,
        required: true
    }
});

module.exports = mongoose.model('UserStore', schema);