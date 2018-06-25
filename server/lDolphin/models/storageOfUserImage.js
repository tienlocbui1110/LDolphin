var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var Schema = new Schema({
    ID: {type: Number, required: true},
    IDUser: {type: Number, required: true, ref: 'User'},
    LinkImage : {type: String, required: true},
    CreatedTime : {type: Date},
    EditedTime : {type: Date},
});

model.exports = mongoose.model('StorageOfUserImage', schema);