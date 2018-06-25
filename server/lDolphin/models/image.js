var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var Schema = new Schema({
    ID: {type: Number, required: true},
    IDImageType: {type: Number, required: true, ref: 'ImageType'},
    IDImageDetail: {type: Number, required: true, ref: 'ImageDetail'},
    IDUser : {type: Number, required: true, ref: 'User'}
});

model.exports = mongoose.model('Image', schema);