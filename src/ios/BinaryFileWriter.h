#import <Cordova/CDVPlugin.h>

@interface BinaryFileWriter : CDVPlugin {}

- (void)writeToFile:(CDVInvokedUrlCommand*)command;
@end
