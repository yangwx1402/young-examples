decode = function(dataString,passwordString){
    var c = JSON.parse(dataString);
    var y = JSON.parse(passwordString);
    var x = y.data.split("");

    var b = {};
    for(i = 0; i < x.length / 2; i++){
        b[x[i]] = x[x.length / 2 + i];
    }
    for (w = 0; w < c.data.length; w++){
        if (c.data[w] && c.data[w].index && c.data[w].index[0]) {
            for (c.data[w].index[0].notin && t.notInNames.push(t.words[w]),
                     M = c.data[w].index[0]._all ? c.data[w].index[0]._all.split("") : [],
                     I = c.data[w].index[0]._pc ? c.data[w].index[0]._pc.split("") : [],
                     C = c.data[w].index[0]._wise ? c.data[w].index[0]._wise.split("") : [],
                     S = [],
                     T = [],
                     k = [],
                     A = 0; A < M.length; A++)
                M[A] && S.push(b[M[A]]),
                I[A] && T.push(b[I[A]]),
                C[A] && k.push(b[C[A]]);
            c.data[w].index[0].all = S.join(""),
                c.data[w].index[0].pc = T.join(""),
                c.data[w].index[0].wise = k.join("")
        }
    }
    return JSON.stringify(c.data);
}