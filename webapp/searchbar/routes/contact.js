var express = require('express');
var request = require('request');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('contact');
});

router.get('/contacts/:name', function(req, res, next) {
  let name = req.params.name;
  var response = request('http://localhost:9010/contacts/' + name, (error, response, body) => {
    res.send(response);
  })
});

router.get('/contacts/:name/details', (req, res, next) => {
  let name = req.params.name;
  let response = request('http://localhost:9010/contacts/' + name + '/details', (error, response) => {
    res.send(response)
  })
})

module.exports = router;
