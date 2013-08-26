# comment1
// comment2
/* comment3 */
/** doc comment */
include "Exception.thrift"

namespace java org.vivus.thrift.test

const bool isValid = true;
const string msg = "thrift test";

typedef string msgList

enum Result {
	FAIL = 0,
	OK = 1,
	UNKNOW = 2
}

senum Message {
	"a",
	"b",
	"c"
}

struct Entity {
	1: required string id;
	2: optional string name;
	bool boolType = true;
	byte byteType;
	i16 i16Type;
	i32 i32Type;
	i64 i64Type;
	double doubleType;
	string stringType;
	binary binaryType;
	slist slistType;
	map<string, string> strMap;
	set<i32> intSet;
	list<string> strList;
	Result result;
	Message message;
}

struct Null {}

exception TestException {
	string code;
}

exception ExTestException {
	string msg;
}

/**
 * TestService
 */
service TestService {
	/**
	 * oneway function
	 */
	oneway void onewayFunc(optional string msg);

	string testFunc();

	Result resultFunc(string id, string name) throws (TestException te);

	string testNull();
}

service ExTestService extends TestService {
	string msgFunc() throws (TestException te, ExTestException ete);
}
