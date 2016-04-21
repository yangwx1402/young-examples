namespace java com.young.java.examples.api
struct NetField{
  1:required string name;
  2:required string value
}

struct NetObject{
  1:required string id;
  2:required i32 type = 0;
  3:map<string,NetField> fileds;
  4:binary stream
}

struct NetResult{
  1:i32 code;
  2:string message;
  3:NetObject obj;
  4:list<NetObject> objs
}

service NetObjectService{
   NetResult put(1:NetObject obj);
   NetResult get(1:string objId);
   NetResult dele(1:string objId)
}
