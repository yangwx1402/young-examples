phantom.outputEncoding="UTF-8";
var casper = require('casper').create({
    verbose: true,
    logLevel: 'error'
});
var fs = require('fs');
var args2 = casper.cli.args;
var cookiefile = args2.slice(0, 1);
var url = args2.slice(1, 2);
var htmlfile = args2.slice(2, 3);
var data = fs.read(cookiefile);
phantom.cookies = JSON.parse(data);
casper.userAgent('Mozilla/5.0 (X11; U; Linux i686; zh-CN; rv:1.9.1.2) Gecko/20090803 Fedora/3.5.2-2.fc11 Firefox/3.5.2');
casper.start('https://zizhanghao.taobao.com/subaccount/monitor/chatRecordJson.htm?action=/subaccount/monitor/ChatRecordQueryAction&eventSubmitDoQueryChatRealtion=anything&_tb_token_=e3aee7347db54&_input_charset=utf-8&chatRelationQuery=%7B%22employeeNick%22%3A%22%E4%B9%90%E8%A7%86%E5%AE%98%E6%96%B9%E6%97%97%E8%88%B0%E5%BA%97%22%2C%22customerNick%22%3A%22%22%2C%22start%22%3A%222017-02-24%22%2C%22end%22%3A%222017-02-24%22%2C%22beginKey%22%3Anull%2C%22endKey%22%3Anull%2C%22employeeAll%22%3Afalse%2C%22customerAll%22%3Atrue%7D&_=1487926556578',function() {
    this.wait(3000, function () {
        this.capture("./json_png.png");
    });
});

casper.then(function() {
    fs.write(htmlfile,JSON.stringify(this.getPageContent()));
    this.echo("success");
});

casper.run();
