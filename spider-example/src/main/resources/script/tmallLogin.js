var casper = require('casper').create({
    verbose: true,
    logLevel: 'error'
});
var fs = require('fs');
casper.userAgent('Mozilla/5.0 (X11; U; Linux i686; zh-CN; rv:1.9.1.2) Gecko/20090803 Fedora/3.5.2-2.fc11 Firefox/3.5.2');
casper.start('https://login.taobao.com/member/login.jhtml', function () {
    this.click("a.forget-pwd.J_Quick2Static")
    this.echo('input form....');
    this.fill('form#J_Form', {
        'TPL_username':'',
        'TPL_password':''
    }, false);
    this.echo('login...');
    this.click("button#J_SubmitStatic.J_Submit");
    this.echo('login finished');
});

casper.then(function () {
    this.wait(3000, function () {
        this.capture("./result.png");
    });
});

casper.then(function () {
    var cookies = this.page.cookies;
    this.echo("cookie.length = " + cookies.length);
    var cookjson = JSON.stringify(phantom.cookies);
    fs.write("./tmall_cookie.json", cookjson);
});

casper.run();