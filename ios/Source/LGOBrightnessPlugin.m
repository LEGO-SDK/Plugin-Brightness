//
//  LGOBrightnessPlugin.m
//  plugin

#import "LGOBrightnessPlugin.h"
#import <LEGO-SDK/LGOCore.h>

@interface LGOBrightnessRequest: LGORequest

@property (nonatomic, assign) float brightness;

@end

@implementation LGOBrightnessRequest

@end

@interface LGOBrightnessResponse: LGOResponse

@property (nonatomic, strong) NSString *text;

@end

@implementation LGOBrightnessResponse

- (NSDictionary *)resData {
    return @{
             };
}

@end

@interface LGOBrightnessperation: LGORequestable

@property (nonatomic, strong) LGOBrightnessRequest *request;

@end

@implementation LGOBrightnessperation

- (LGOResponse *)requestSynchronize {
    if (self.request.brightness <= 1.0 && self.request.brightness >= 0.0) {
        [UIScreen mainScreen].brightness = self.request.brightness;
    }
    return [[LGOBrightnessResponse new] accept:nil];
}

- (void)requestAsynchronize:(LGORequestableAsynchronizeBlock)callbackBlock {
    callbackBlock([self requestSynchronize]);
}

@end

@implementation LGOBrightnessPlugin

- (LGORequestable *)buildWithDictionary:(NSDictionary *)dictionary context:(LGORequestContext *)context {
    LGOBrightnessperation *operation = [LGOBrightnessperation new];
    operation.request = [LGOBrightnessRequest new];
    operation.request.brightness = [dictionary[@"brightness"] isKindOfClass:[NSNumber class]] ? [dictionary[@"brightness"] floatValue] : 0;
    return operation;
}

- (LGORequestable *)buildWithRequest:(LGORequest *)request {
    if ([request isKindOfClass:[LGOBrightnessRequest class]]) {
        LGOBrightnessperation *operation = [LGOBrightnessperation new];
        operation.request = (LGOBrightnessRequest *)request;
        return operation;
    }
    return nil;
}

+ (void)load {
    [[LGOCore modules] addModuleWithName:@"Plugin.Brightness" instance:[self new]];
}

@end
