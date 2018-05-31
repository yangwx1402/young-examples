function getData(passwordString, dataString) {
    var password = JSON.parse(passwordString);
    var indexs = JSON.parse(dataString);
    var arr = password.data.split('');
    var objPass = {};
    for (var i = 0; i < arr.length / 2; i++) {
        objPass[arr[i]] = arr[arr.length / 2 + i];
    }
    for (var i = 0; i < indexs.data.length; i++) {
        if (indexs.data[i] && indexs.data[i].index && indexs.data[i].index[0]) {
            if (indexs.data[i].index[0].notin) {
                self.notInNames.push(self.words[i]);
            }
            var all = indexs.data[i].index[0]._all ? indexs.data[i].index[0]._all.split('') : [];
            var pc = indexs.data[i].index[0]._pc ? indexs.data[i].index[0]._pc.split('') : [];
            var wise = indexs.data[i].index[0]._wise ? indexs.data[i].index[0]._wise.split('') : [];
            var allArr = [];
            var pcArr = [];
            var wiseArr = [];
            for (var j = 0; j < all.length; j++) {
                all[j] && allArr.push(objPass[all[j]]);
                pc[j] && pcArr.push(objPass[pc[j]]);
                wise[j] && wiseArr.push(objPass[wise[j]]);
            }
            indexs.data[i].index[0].all = allArr.join('');
            indexs.data[i].index[0].pc = pcArr.join('');
            indexs.data[i].index[0].wise = wiseArr.join('');
        }
    }
    return JSON.stringify(indexs);
}