/**********************************************************************************************************************
****************************************************     NODE     *****************************************************
***********************************************************************************************************************/
* SCRIPT NODES
    + data.context.entities.en_InitialMsg
    + Teting Custom ID
        - Fails if Does Not Exist
        - Only Exists when MicroSite Creates Connection
        - Latest Scripts
            koreDebugger.log(JSON.stringify(context));
            var testId = "TestId";
            var customId = context.session.UserContext;
            if (customId === null) {
                koreDebugger.log("UserContext is Null");
            } else {
                testId = customId.customData.id;
                koreDebugger.log("UserContext CustomData ID is = " + testId);
            }
            koreDebugger.log("\n\n\tCustom ID = " + testId);
* URLS
    + Custom Widget
        - https://cai-preprod.express-scripts.com/webclient/UI/dist/kore-ai-sdk.min.css
        - https://cai-preprod.express-scripts.com/api/platform/websdkjs/15bd00620a3f4438a58e62041a1621ae4b84950cdaa249a391d9ae2d954bb1eastc4
            <link rel='stylesheet' href='https://cai-preprod.express-scripts.com:443/webclient/UI/dist/kore-ai-sdk.min.css'></link>
            <script src='https://cai-preprod.express-scripts.com:443/api/platform/websdkjs/15bd00620a3f4438a58e62041a1621ae4b84950cdaa249a391d9ae2d954bb1eastc4'></script>
            <script>KoreSDK.show(KoreSDK.chatConfig);</script>
    + Banner
        - https://www.pinterest.com/pin/832673418594517322/
    + Background
        - 


/**********************************************************************************************************************
****************************************************     KORE     *****************************************************
***********************************************************************************************************************/
* https://developer.kore.ai/docs/bots/sdks/kore-ai-web-sdk-tutorial/
    + Passing Mapped Identities
        - The Web SDKs support the passing of mapped identities of the users when they 
          switch from one identity to another while interacting with the bot. 
        - This process allows the users to continue any ongoing conversation initiated using a previous identity.
            + EXAMPLE
                - CONNECTING TWO USER SESSIONS TO CONTINUE
                    ~ A user may have started the conversation with the bot 
                        - Using an anonymous or randomly generated identity. 
                    ~ After exchanging messages, the user may become authenticated or known by logging in. 
                      At this point, the user’s known identity can be passed to the bot from the SDK as part of the 
                        - ‘JWT Grant API’ call using the parameter identityToMerge. 
                    ~ The Platform uses this information to merge the user identities & allows the user to 
                      resume an ongoing conversation using the new known identity.
                            {   // JwtGrant Reg - identityToMerge field
                                "iat": 1611810186883,
                                "exp": 1611813786.883,
                                "aud": "https://idproxy.kore.com/authorize",
                                "iss": "cs-d3042d3e-7da4-55da-a94d-783349270cc0",
                                "sub": "knowuser1@test.com",
                                "isAnonymous": "false",
                                "identityToMerge": "anonymoususer1@test.com"
                            }
    + Adding Custom Data & Meta Tags to Bot User Session
        - These tags will be added to the Conversation Session as soon as it is created.
            + EXAMPLE #1
                    botOptions.botInfo = {
                        "name": "<bot_name>",
                        "_id": "<bot_id",
                        "customData": { "intent": "SMSFlow" },
                        "metaTags": {
                            "messageLevelTags": [{ "name": "tag1", value: "message" }],
                            "sessionLevelTags": [{ "name": "tag2", value: "session" }],
                            "userLevelTags": [{ "name": "tag3", value: "user" }]
                        }
                    };
            + EXAMPLE #2 
                ~ Add multiple values to customData.
                ~ Details like phone number, address, or location, are examples for customData.
                    botOptions.botInfo = {
                        "name": "Onconnect check",
                        "_id": 'st-XXXX-832e-',
                        "customData": {
                            "name": "John",
                            "age": 30,
                            "cars": {
                                "car1": "Ford",
                                "car2": "BMW",
                                "car3": "Fiat"
                            }
                        }
                    };
                ~ CustomData can then be accessed from BotUserSession object.
                    • Context
                        ↦ BotUserSession
                            ↦ lastMessage
                                ↦ messagePayload
                                    ↦ botInfo
                                        ↦ customData
                                            ↦ cars
* https://developer.kore.ai/docs/bots/bot-builder-tool/dialog-task/using-session-and-context-variables-in-tasks/

For a message node, go to advanced settings and add a channel specific message in Java Script mode.
// Prints big JSON object
print(JSON.stringify(context));
You may also try:
print(JSON.stringify(context.session.UserContext));
Or: print(JSON.stringify(context.session.BotUserSession));




———-—————————————————————
You can PUT a value into the session using the UserSession variable where the key is defined as fullName based on the GET from the two UserContext variables.

var name = UserContext.get("firstName") + UserContext.get("lastName");
UserSession.put("fullName") = name;
———-—————————————————————


On a message node.
print(JSON.stringify(context.session));


———-—————————————————————

customData can be accessed from lastMessage under the BotUserSession of the context object. This data will be specific to the user using webSDK and will last for the user session.

Multiple values can also be added to the customData in the index.html file as key-value pairs:

botOptions.botInfo = {name:"<bot_name>",
"_id":"<bot_id",
customData:{"name":"John",
"age":30,
"cars": {"car1":"Ford",
"car2":"BMW",
"car3":"Fiat"
}
}
};

———-—————————————————————
botoptions.useridentity
In the same, you pass any unique identifier like time-stamp/ some unique string. If you send some value which was previously sent, platform will assume it is the old user trying to communicate and may show chat history etc.

———-—————————————————————

Show messages without user intraction.
* https://community.kore.ai/t/how-to-start-a-task-immediately-when-we-open-chat-window/338

Start intent immediately on connect & display chat:

You can achieve this requirement using Kore WebSDK.

1. In the Kore WebSDK folder, in kore-bot-sdk-client file, under onOpenWSConnection function, we need to write the below code to send a message to the bot from WebSDK.
var message = {
    'message': {
        'body': '<Dialog Name>'
    },
    'resourceid': '/bot.message'
};

window.currKotrebotInstance = this;

setTimeout(
        function({
                window
                .currKotrebotInstance
                .sendMessage(message)
            },
            2000
        );
2.Now save the file and open Index.html file. We can see the triggered dialog as when the chat window opens.
* https://developer.kore.ai/docs/bots/sdks/bots-platform-api-reference/

———-—————————————————————

Response
The follow sample JSON response shows the accessToken and user associated with that token.

{
"authorization": {
"accessToken": "ZdV2OL_UZ_MvHog-rs8k9KJFNWBICvquSc3jpeaRDE_-",
// access token to user at /api/rtm
"token_type": "bearer",
"expiresDate": "2019-06-28T06:52:23.160Z",
//expiry date for access token
"issuedDate": "2019-02-28T06:52:23.160Z" 
//access token issuedDate
},
"userInfo": {
"userId": "u-4f6c68e0-551a-5dd9-a33a-1af3dc9cadcc", 
// user id which is unique for the user and can be used at bot messages api
"accountId": "5c66514d09ab3565deb2e30a", 
// account id in which the user is present
"orgId": "o-88aad7f1-0d32-5765-93d7-f40c80402114",
// organization id of the user
"identity": "cs-5b08ed1e-5fa7-5aaa-9c21-28bf8c90b739/admin1212@qakore.xyz", 
//identity of user from the channel perspective
"enrollType": "free", 
//Enrollment type (Free/ Paid etc)
"managedBy": "5c66514d09ab3565deb2e30a", 
//(The account id)
"fName": "", 
//first name of the user
// (fetched if the user is registered on the platform)
"lName": "" 
//last name of the user
// (fetched if the user is registered on the platform)
}
}



———-—————————————————————
RTM Client Events

This section describes the RTM Client Event JSON responses sent to the Kore.ai Bots Platform from the client app over a web socket. 
    Event Type: /bot.message 
    Description: Triggered when a user posts a message. The following payload is used to send a user-typed message to the Bots Platform.

{
// CHECK THESE ID VALUES 
"clientMessageId": 1466692440896,
"id": 1466692440896
"message": {
"body": "Here is the message.",
"attachments": [

]
},
"resourceid": "/bot.message",
"botInfo": {
"chatBot": "CNN",
"taskBotId": "st-8aaf0939-c34a-5976-8e2e-5c91e685b2ce"
},
* https://developer.kore.ai/docs/bots/bot-builder-tool/dialog-task/context-object/
Object: Context 
Key: entities
Syntax: context.entities.<< entity name >>In the following code example, the context.entities object is used to access values for the amount and account names as a confirmation for a funds transfer between accounts
var today = new Date();
if(today.getHours() < 21)
{
print("You have requested to transfer " +context.entities.Amount + 
" USD from " +context.entities.FromAccountName +
" to " +context.entities.ToAccountName + " account. " + 
" Your funds will be shown immediately though transfer will be shown in tomorrow's date in your transaction history. Shall I go ahead? ");
}
else
{
print("You have requested to transfer " +context.entities.Amount + 
" USD from " +context.entities.FromAccountName +
" to " +context.entities.ToAccountName + " account. Shall I go ahead? " +
context.accdata[0].transactions.length);
}

———-—————————————————————

Syntax: 
context.<< node name >>.response.body
In the following code example, the response from a Service node is displayed to the end-user in a Message node.

print(JSON.stringify(context.fetchopportunitiesnode.response.body));

———-—————————————————————

Developer Defined Key 
Description:
A key/value pair defined by the developer.
Syntax: 
context.<< varName >>
Example:
context.customerId


———-—————————————————————



URL For Trigger Form 
The Process Apps now allows you to copy the URL for a form to trigger the process flow. You can copy the form URL from the property panel of the Digital Form and share as appropriate and this form is hosted by Kore.ai. Learn more.

———-—————————————————————

WebHook Node
Used for server-side validation, executing business logic, or making backend server API calls. To use this node, you must have installed the SDK Tool Kit. For more information, refer to Working with the WebHook Node.
While any dialog starts at the Intent Node, it is advisable to end it with a Message Node to give a sense of closure to the user as well as to the VA internally.

* https://developer.kore.ai/docs/bots/bot-builder-tool/dialog-task/context-object/

"UserSession":
{},
"BotUserSession":
{
    "isReturningUser": true,
    "lastMessage":
    {
        "channel": "rtm",
        "messagePayload":
        {
            "clientMessageId": 1501244152843,
            "message":
            {
                "body": "1234"
            },
            "resourceid": "/bot.message",
            "botInfo":
            {
                "chatBot": "Kore Banking Documentation",
                "taskBotId": "st-b4a22e86-XXXX-XXXX-b888-e106d083a251"
            },
            "client": "botbuilder",
            "meta":
            {
                "timezone": "America/New_York",
                "locale": "en-US"
            },
            "id": 1501244152843
        }
    },
    "lastUserMessageTime": "2021-07-12T07:07:17.278Z"
},
"opts":
{
    "userId": "u-4b9f02a3-XXXX-XXXX-b5cc-6df81c0af603",
    "streamId": "st-b4a22e86-XXXX-XXXX-b888-e106d083a251"
}
}
},



* https://developer.kore.ai/docs/bots/sdks/message-templates

———-—————————————————————

Context: Compare a context object in the dialog with a specific value using one of these operators: Exists, equals to, greater than equals to, less than equals to, not equal to, greater than, and less than. For example, Context.entity.PassengerCount (Context object) greater than (operator) 5 (specified value).


———-—————————————————————

CONTEXT OBJECT
Script to access the context tags are:
+ Script Node
+ Advanced Prompts
+ Run a Script Option
You can emit output context tags from any place where JavaScript is written (script node, advanced prompts, run a script option, etc.) using the
contextTags.add(string value).

From the current context: 
context.currentTags.tags
From the previous context:
context.historicTags[0].tags

———-—————————————————————


 


Kindest regards,
- Nate V.
