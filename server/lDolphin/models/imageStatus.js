var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var Schema = new Schema({
    ID: {type: Number, required: true},
    Name: {type: String, required: true}
});

model.exports = mongoose.model('ImageStatus', schema);