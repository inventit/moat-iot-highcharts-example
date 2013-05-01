/*
 * JobServiceID:
 * urn:moat:${APPID}:pvdemo:upload-sensing-data:1.0
 * 
 * Description: Sensing Data Upload Function.
 * Reference: https://docs.google.com/a/yourinventit.com/document/d/1kdHxMp2VcZWcDnJ4YZqmW_aEYQt94ySrlityZQK2g6w/edit#heading=h.gmcqk4pexngi
 */
var moat = require('moat');
var context = moat.init();
var session = context.session;
var clientRequest = context.clientRequest;

session.log('upload-sensing-data', 'Start Upload Sensing Data!');

// Result should be returned
var objects = clientRequest.objects;
if (objects.length == 0) {
	session.log('Device sends wrong information.')
	throw "No Result object!";
}

session.log('upload-sensing-data', 'OK! ' + objects.length + ' events arrived.');
session.notifyAsync({
	success: true,
	data: objects
});
