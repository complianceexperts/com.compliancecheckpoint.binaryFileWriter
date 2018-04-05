Binary file writer plugin

Created by: Dilhan Jayathilake 

com.compliancecheckpoint.binaryFileWriter

Usage:

Copy following function and past it to your project,

function writeToFile(fileName, base64Data, onSuccess, onFail){

        cordova.exec(function (fileResult) {

            if (onSuccess)
                onSuccess(fileResult);

        }, function (err) {

            if (onFail)
                onFail(err);

        }, "BinaryFileWriterPlugin", "writeToFile", [{base64data: base64Data, fileName: fileName}]);

}


Example:

writeToFile('myFile.txt', btoa('base 64 data'), function(fileResult){
	console.log('New file URL: ' + fileResult.fileUri);
},
function(fileError){
	console.log('error: ' + JSON.stringify(fileError))
});
