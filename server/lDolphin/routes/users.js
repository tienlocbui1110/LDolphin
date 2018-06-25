var express = require('express');
var router = express.Router();

var userController = require('../controllers/userController');

/* GET users listing. */
router.get('/', function(req, res) {
    userController.getAll(function(users) {
        users = users;
    });
});

router.get('/:id', function(req, res) {
    let id = req.params.id;
    userController.getById(id, function(user) {
        user = user;
    });
});

module.exports = router;