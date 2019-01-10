var express = require('express')
var router = express.Router()

const Article = require("../models/article")
const User = require("../models/user")

// http://localhost:3000/article?userId=abcxyz&page=2 ==> Get article
router.get('/', function (req, res) {
  res.contentType('application/json');
  res.setHeader("Access-Control-Allow-Origin", "*");
  // Get page of article
  limit = 10;
  var page = req.query.page;
  if (!page || page <= 0)
    page = 1;
  // is specific user ID ?
  if (!req.query.userId) {
    // request all article
    // This is not scalable
    Article.find({}, null, { skip: limit * (page - 1), limit: limit }, function (err, results) {
      if (err) {
        res.status = 400;
        res.json({
          "status": "error",
          "message": err
        });
      } else {
        res.status = 200;
        res.json({
          "status": "ok",
          "message": "Successfully",
          "data": results
        });
      }
    });
  } else {
    // request article that fit userId
    Article.find({}).populate({
      path: "user",
      match: { _id: req.query.userId },
      options: { skip: limit * (page - 1), limit: limit }
    }).exec((err, articles) => {
      if (err) {
        res.status = 400;
        res.json({
          "status": "error",
          "message": err
        });
      } else {
        res.status = 200;
        res.json({
          "status": "ok",
          "message": "Successfully",
          "data": articles
        });
      }
    });
  }
})

// http://localhost:3000/article ==> Post new Article
// Param (required) : userId

router.post('/', function (req, res) {
  if (req.body.userId) {
    // Posting  
    var user = User.findOne({ _id: req.body.userId }, (err, res) => {
      if (err) {
        errorUserSignature(res, err);
      } else {
        var article = new Article({
          imagePath: req.body.imgPath,
          description: req.body.description,
          user: res
        });
        article.save(function (err, result) {
          if (err) {
            errorUserSignature(res, err);
          } else {
            res.statusCode = 200;
            res.contentType('application/json');
            res.setHeader("Access-Control-Allow-Origin", "*");
            res.json({
              "status": "ok",
              "message": "Created"
            });
          }
        });
      }
    });
  } else {
    errorUserSignature(res);
  }
})


// http://localhost:3000/article ==> Post new Article
// Param (required) : userId

router.delete('/', function (req, res) {
  res.contentType('application/json');
  res.setHeader("Access-Control-Allow-Origin", "*");

  if (req.body.userId && req.body.articleId) {
    // Delete
    Article.findOne({ _id: req.body.articleId }, function (err, result) {
      if (err) {
        errorUserSignature("Failed to delete article");
      } else {
        if (result.user._id == req.body.userId) {
          Article.deleteOne({ _id: req.body.articleId });
          res.status = 200;
          res.json({
            "status": "ok",
            "message": "Deleted"
          });
        } else {
          res.status = 401;
          res.json({
            "status": "error",
            "message": "Unauthorized"
          });
        }

      }
    })
  } else {
    errorUserSignature(res);
  }
})

function errorUserSignature(res, message) {
  if (!message)
    message = "Bad Request."
  res.statusCode = 400;
  res.contentType('application/json');
  res.setHeader("Access-Control-Allow-Origin", "*");
  res.json({
    "status": "error",
    "message": "Bad Request."
  });
}

module.exports = router