var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var Schema = new Schema({
    ID: {type: Number, required: true},
    IDStatus: {type: Number, required: true, ref: 'ImageStatus'},
    LinkLargeImage : {type: String, required: true},
    LinkThumbnailImage : {type: String, required: true},
    Name : {type: String, required: true},
    Title : {type: String},
    Tag : {type: String},
    Size : {type: Number},
    CountLike : {type: Number},
    CountComment : {type: Number},
    ShortLink : {type: String},
});

model.exports = mongoose.model('ImageDetail', schema);