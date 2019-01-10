var mongoose = require('mongoose')
var Schema = mongoose.Schema

var UserSchema = new Schema({
    _id: {
        type: String,
        unique: true,
        required: true,
    },
    email: {
        type: String,
        required: true,
        unique: true
    },
    name: {
        type: String,
        required: true,
    }
});

module.exports = mongoose.model('User', UserSchema);