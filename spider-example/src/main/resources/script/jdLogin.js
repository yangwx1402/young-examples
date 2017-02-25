var casper = require('casper').create({
    verbose: true,
    logLevel: 'error'
});
var args2 = casper.cli.args;
var username = args2.slice(0, 1);
var password = args2.slice(1, 2);
var resultimg = args2.slice(2, 3);
var cookiefile = args2.slice(3, 4);
var fs = require('fs');
casper.userAgent('Mozilla/5.0 (X11; U; Linux i686; zh-CN; rv:1.9.1.2) Gecko/20090803 Fedora/3.5.2-2.fc11 Firefox/3.5.2');
casper.start('https://passport.jd.com/new/login.aspx?ReturnUrl=http://shop.chat.jd.com/index.action', function () {
    this.click("div.login-tab.login-tab-r")
    this.fill('form#formlogin', {
        'loginname': username,
        'nloginpwd': password
    }, false);
    this.click("a#loginsubmit.btn-img.btn-entry");
});

casper.then(function () {
    this.wait(5000,function(){
        if(this.exists('div[class="clear logo"]')){
            this.echo('successed');
            var cookjson = JSON.stringify(phantom.cookies);
            fs.write(cookiefile, cookjson);
        }else{
            this.echo('failed');
        }
    });
});

casper.then(function () {
    this.wait(3000, function () {
        this.capture(resultimg);
    });
});

casper.run();