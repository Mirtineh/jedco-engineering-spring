(this["webpackJsonp@coreui/coreui-free-react-admin-template"]=this["webpackJsonp@coreui/coreui-free-react-admin-template"]||[]).push([[9],{557:function(e,t,s){"use strict";s.d(t,"a",(function(){return n}));s(0);const n=()=>({cm_url:"/Engineering",um_url:"/Engineering",file_url:""})},561:function(e,t,s){"use strict";s.d(t,"i",(function(){return i})),s.d(t,"g",(function(){return a})),s.d(t,"h",(function(){return o})),s.d(t,"c",(function(){return l})),s.d(t,"n",(function(){return d})),s.d(t,"m",(function(){return u})),s.d(t,"e",(function(){return j})),s.d(t,"j",(function(){return h})),s.d(t,"d",(function(){return b})),s.d(t,"o",(function(){return m})),s.d(t,"a",(function(){return O})),s.d(t,"b",(function(){return x})),s.d(t,"f",(function(){return g})),s.d(t,"l",(function(){return p})),s.d(t,"k",(function(){return f}));var n=s(557),r=s(563),c=s.n(r);const i=e=>{const t=Object(n.a)().um_url,s={headers:{Authorization:"Bearer ".concat(e)}};return c.a.get(t+"/UserRole/userRoleList",s).then((e=>e.data))},a=e=>{const t=Object(n.a)().um_url,s={headers:{Authorization:"Bearer ".concat(e)}};return c.a.get(t+"/UserAction/userActionList",s).then((e=>e.data))},o=(e,t)=>{const s=Object(n.a)().um_url,r={headers:{Authorization:"Bearer ".concat(e)}};return c.a.get(s+"/roleDefinition/definedRolesList?userRoleId="+t,r).then((e=>e.data))};function l(e,t){const s=Object(n.a)().um_url;return c()({method:"post",url:s+"/roleDefinition/defineRole",headers:{Authorization:"Bearer ".concat(e)},data:t}).then((e=>e.data))}function d(e,t){const s=Object(n.a)().um_url;return c()({method:"put",url:s+"/UserRole/updateUserRole",headers:{Authorization:"Bearer ".concat(e)},data:{id:t.id,name:t.name,description:t.description}}).then((e=>e.data))}function u(e,t){const s=Object(n.a)().um_url;return c()({method:"put",url:s+"/roleDefinition/updateDefinedActions",headers:{Authorization:"Bearer ".concat(e)},data:t}).then((e=>e.data))}function j(e,t){const s=Object(n.a)().um_url;return c()({method:"delete",url:s+"/roleDefinition/deleteUserRole?UserRoleID="+t,headers:{Authorization:"Bearer ".concat(e)}}).then((e=>e.data))}const h=e=>{const t=Object(n.a)().um_url,s={headers:{Authorization:"Bearer ".concat(e)}};return c.a.get(t+"/users/listUsers",s).then((e=>e.data))},b=(e,t)=>{const s=Object(n.a)().um_url;return c()({method:"post",url:s+"/users/registerUser",headers:{Authorization:"Bearer ".concat(e)},data:t}).then((e=>e.data))},m=(e,t)=>{const s=Object(n.a)().um_url;return c()({method:"put",url:s+"/users/updateUserProfile",headers:{Authorization:"Bearer ".concat(e)},data:t}).then((e=>e.data))},O=e=>{const t=Object(n.a)().um_url;return c.a.get(t+"/User/activationNumCheck?activationNO="+e).then((e=>e.data))},x=e=>{const t=Object(n.a)().um_url;return c()({method:"post",url:t+"/User/setUserNameAndPassword",data:e}).then((e=>e.data))};function g(e,t){const s=Object(n.a)().um_url;return c()({method:"delete",url:s+"/users/deleteUser?userId="+t,headers:{Authorization:"Bearer ".concat(e)}}).then((e=>e.data))}function p(e,t){const s=Object(n.a)().um_url;return c()({method:"put",url:s+"/users/suspendUser?userId="+t,headers:{Authorization:"Bearer ".concat(e)}}).then((e=>e.data))}function f(e,t){const s=Object(n.a)().um_url;return c()({method:"put",url:s+"/users/activateUser?userId="+t,headers:{Authorization:"Bearer ".concat(e)}}).then((e=>e.data))}},669:function(e,t,s){"use strict";s.r(t);var n=s(0),r=s.n(n),c=s(552),i=s(137),a=s(89),o=s(561),l=s(684),d=s(678),u=s(679),j=s(676),h=s(670),b=s(11);const m=e=>{switch(e){case 1:return"success";case"Inactive":return"secondary";case 2:return"warning";case 3:case 4:case 5:return"danger";default:return"primary"}},O=r.a.forwardRef((function(e,t){return Object(b.jsx)(h.a,{direction:"up",ref:t,...e})}));t.default=()=>{const[e,t]=Object(n.useState)(!1),[s,h]=Object(n.useState)(!0),[x,g]=Object(n.useState)(""),[p,f]=r.a.useState(!1),[N,S]=Object(n.useState)({}),[v,y]=Object(n.useState)([]),k=()=>{t(!e),C(""),B(""),P(""),q(""),V(""),H(""),Q(""),h(!0),ee(!0),Z(!1)},U=Object(a.b)();Object(n.useEffect)((()=>{const e=[];e.push("firstName","lastName",{key:"email",_style:{width:"20%"}},"phoneNo",{key:"username",label:"userName",sorter:!1,filter:!1},"userRole","userStatus",{key:"show_details",label:"Details",_style:{width:"1%"},sorter:!1,filter:!1}),U.login.roles.includes("RESET_USER")&&e.push({key:"update",label:"Update",sorter:!1,filter:!1}),U.login.roles.some((e=>"DELETE_USER"===e||"SUSPEND_USER"===e||"ACTIVATE_USER"===e))&&e.push({key:"delete",label:"Action",sorter:!1,filter:!1}),y(e)}),[]);const E=(e,t)=>{f(!0),S(e),g(t)},R=e=>{Object(o.f)(U.login.token,e).then((e=>{e.status?(i.NotificationManager.success(e.message,"Success",3e3),te(),f(!1),re([])):i.NotificationManager.error(e.message,"Failed!",3e3)})).catch((e=>{console.log(e),403==e.response.status?i.NotificationManager.error("You are not allowed to perform this task","Failed!",3e3):i.NotificationManager.error("Unable to try User Delete, check your connection","Failed!",3e3)}))},A=e=>{Object(o.l)(U.login.token,e).then((e=>{e.status?(i.NotificationManager.success(e.message,"Success",3e3),te(),f(!1),re([])):i.NotificationManager.error(e.message,"Failed!",3e3)})).catch((e=>{console.log(e),403==e.response.status?i.NotificationManager.error("You are not allowed to perform this task","Failed!",3e3):i.NotificationManager.error("Unable to try User Suspend, check your connection","Failed!",3e3)}))},w=e=>{Object(o.k)(U.login.token,e).then((e=>{e.status?(i.NotificationManager.success(e.message,"Success",3e3),te(),f(!1),re([])):i.NotificationManager.error(e.message,"Failed!",3e3)})).catch((e=>{console.log(e),403==e.response.status?i.NotificationManager.error("You are not allowed to perform this task","Failed!",3e3):i.NotificationManager.error("Unable to try User Suspend, check your connection","Failed!",3e3)}))},[_,F]=Object(n.useState)([]),[I,M]=Object(n.useState)([]),[z,C]=Object(n.useState)(""),[D,B]=Object(n.useState)(""),[L,P]=Object(n.useState)(""),[T,q]=Object(n.useState)(""),[Y,V]=Object(n.useState)(""),[G,J]=Object(n.useState)(""),[W,H]=Object(n.useState)(""),[K,Q]=Object(n.useState)(""),[X,Z]=Object(n.useState)(!1),[$,ee]=Object(n.useState)(!0),te=()=>{Object(o.j)(U.login.token).then((e=>{F(e),console.log(e)})).catch((e=>console.log(e)))};Object(n.useEffect)((()=>{te()}),[]),Object(n.useEffect)((()=>{Object(o.i)(U.login.token).then((e=>{M(e),console.log(e)})).catch((e=>console.log(e)))}),[]);const[se,ne]=Object(n.useState)([]),re=e=>{const t=se.indexOf(e);let s=se.slice();-1!==t?s.splice(t,1):s=[...se,e],ne(s)};return Object(b.jsxs)(b.Fragment,{children:[Object(b.jsxs)(c.J,{show:e,onClose:k,size:"lg",backdrop:!0,closeOnBackdrop:!1,children:[Object(b.jsx)(c.M,{size:"modal-lg",closeButton:!0,children:Object(b.jsx)("h4",{children:s?"Add User":"Update User"})}),Object(b.jsxs)(c.O,{children:[Object(b.jsx)(c.i,{xs:"12",md:"12",children:Object(b.jsx)(c.e,{children:Object(b.jsx)(c.f,{children:Object(b.jsxs)("form",{onSubmit:e=>{e.preventDefault(),console.log("handle Submit here");const t=[];if($&&t.push("email"),X&&t.push("sms"),s){const e={roleId:Y,firstName:z,lastName:D,email:L,phoneNo:T,username:W,password:K};Object(o.d)(U.login.token,e).then((e=>{e.status?(i.NotificationManager.success(e.message,"Success",3e3),te(),k()):i.NotificationManager.error(e.message,"Failed!",3e3)})).catch((e=>{console.log(e),403==e.response.status?i.NotificationManager.error("You are not allowed to perform this task","Failed!",3e3):i.NotificationManager.error("Unable to try User Create, check your connection","Failed!",3e3)}))}else{const e={userId:G,userRoleId:Y,firstName:z,lastName:D,email:L,phone:T,password:K};Object(o.o)(U.login.token,e).then((e=>{e.status?(i.NotificationManager.success(e.message,"Success",3e3),te(),k()):i.NotificationManager.error(e.message,"Failed!",3e3)})).catch((e=>{console.log(e),403==e.response.status?i.NotificationManager.error("You are not allowed to perform this task","Failed!",3e3):i.NotificationManager.error("Unable to try User Create, check your connection","Failed!",3e3)}))}},children:[Object(b.jsxs)(c.t,{row:!0,children:[Object(b.jsx)(c.i,{md:"2",children:Object(b.jsx)(c.F,{htmlFor:"text-input",children:"First Name"})}),Object(b.jsx)(c.i,{xs:"12",md:"4",children:Object(b.jsx)(c.y,{id:"text-input",name:"text-input",onInput:e=>C(e.target.value),value:z,placeholder:"Enter First Name",required:!0})}),Object(b.jsx)(c.i,{md:"2",children:Object(b.jsx)(c.F,{htmlFor:"text-input",children:"Last Name"})}),Object(b.jsx)(c.i,{xs:"12",md:"4",children:Object(b.jsx)(c.y,{id:"text-input",name:"text-input",onInput:e=>B(e.target.value),value:D,placeholder:"Enter Last Name",required:!0})})]}),Object(b.jsxs)(c.t,{row:!0,children:[Object(b.jsx)(c.i,{md:"2",children:Object(b.jsx)(c.F,{htmlFor:"email-input",children:"Phone Number"})}),Object(b.jsx)(c.i,{xs:"12",md:"4",children:Object(b.jsx)(c.y,{id:"email-input",name:"email-input",onInput:e=>q(e.target.value),value:T,placeholder:"Enter Phone Number",required:!0})}),Object(b.jsx)(c.i,{md:"2",children:Object(b.jsx)(c.F,{htmlFor:"email-input",children:"Email"})}),Object(b.jsx)(c.i,{xs:"12",md:"4",children:Object(b.jsx)(c.y,{type:"email",id:"email-input",name:"email-input",onInput:e=>P(e.target.value),value:L,placeholder:"Enter Email",autoComplete:"email",required:!0})})]}),Object(b.jsxs)(c.t,{row:!0,children:[Object(b.jsx)(c.i,{md:"2",children:Object(b.jsx)(c.F,{htmlFor:"email-input",children:"Username"})}),Object(b.jsx)(c.i,{xs:"12",md:"4",children:Object(b.jsx)(c.y,{id:"username-input",name:"username-input",onInput:e=>H(e.target.value),value:W,placeholder:"Enter Username",required:!0})}),Object(b.jsx)(c.i,{md:"2",children:Object(b.jsx)(c.F,{htmlFor:"email-input",children:"Password"})}),Object(b.jsx)(c.i,{xs:"12",md:"4",children:s?Object(b.jsx)(c.y,{id:"password-input",name:"password-input",onInput:e=>Q(e.target.value),value:K,placeholder:"Enter Password",required:!0}):Object(b.jsx)(c.y,{id:"password-input",name:"password-input",onInput:e=>Q(e.target.value),value:K,placeholder:"Enter Password"})})]}),Object(b.jsx)(c.t,{row:!0}),Object(b.jsxs)(c.t,{row:!0,children:[Object(b.jsx)(c.i,{md:"2",children:Object(b.jsx)(c.F,{htmlFor:"selectLg",children:"Select Role"})}),Object(b.jsx)(c.i,{xs:"12",md:"4",size:"lg",children:Object(b.jsxs)(c.P,{custom:!0,name:"selectLg",id:"selectLg",value:Y,onChange:e=>{V(e.target.value)},children:[Object(b.jsx)("option",{value:0,children:"--Select User Role--"},-1),I.map(((e,t)=>Object(b.jsx)("option",{value:e.id,children:e.name},t)),void 0)]})}),Object(b.jsx)(c.i,{tag:"label",sm:"2",md:"2",className:"col-form-label",children:"Notification"}),Object(b.jsxs)(c.i,{sm:"4",children:[Object(b.jsxs)(c.t,{variant:"custom-checkbox",inline:!0,children:[Object(b.jsx)(c.z,{custom:!0,id:"inline-checkbox1",name:"inline-checkbox1",value:"sms",disabled:!0,checked:X,onClick:e=>{Z(e.target.checked),console.log("Sms selected"+e.target.checked)}}),Object(b.jsx)(c.F,{variant:"custom-checkbox",htmlFor:"inline-checkbox1",children:"SMS"}),Object(b.jsx)(c.t,{variant:"custom-checkbox",inline:!0})]}),Object(b.jsxs)(c.t,{variant:"custom-checkbox",inline:!0,children:[Object(b.jsx)(c.z,{custom:!0,id:"inline-checkbox2",name:"inline-checkbox2",value:"email",checked:$,onClick:e=>{ee(e.target.checked),console.log("Email selected"+e.target.checked)}}),Object(b.jsx)(c.F,{variant:"custom-checkbox",htmlFor:"inline-checkbox2",children:"Email"})]})]})]}),Object(b.jsx)(c.g,{}),Object(b.jsxs)("div",{className:"d-flex justify-content-between",children:[Object(b.jsx)("button",{type:"button",className:"btn btn-outline-danger m-2 btn-md",onClick:k,children:"Cancel"}),Object(b.jsx)("button",{type:"submit",className:"btn btn-outline-success m-2 btn-md",children:"Submit"})]})]})})})}),Object(b.jsx)(c.i,{xs:"12",md:"6"})]})]}),Object(b.jsx)(c.O,{children:Object(b.jsx)(c.i,{children:Object(b.jsxs)(c.e,{children:[Object(b.jsx)(c.h,{children:Object(b.jsxs)("div",{className:"d-flex justify-content-between",children:[Object(b.jsx)("h4",{children:"Users"}),Object(b.jsx)("div",{className:"d-flex justify-content-end",children:U.login.roles.includes("REGISTER_USER")?Object(b.jsx)("button",{type:"button",className:"btn btn-outline-success m-2 btn-md",onClick:k,children:"Add User"}):""})]})}),Object(b.jsx)(c.f,{children:Object(b.jsx)(c.m,{items:_,fields:v,tableFilter:!0,itemsPerPageSelect:!0,itemsPerPage:5,hover:!0,size:"sm",striped:!0,bordered:!0,outlined:!0,pagination:!0,scopedSlots:{userStatus:e=>Object(b.jsx)("td",{children:Object(b.jsx)(c.a,{color:m(e.userStatusId),children:e.userStatus})}),role:e=>Object(b.jsx)("td",{children:e.userRoleName}),userName:e=>Object(b.jsx)("td",{children:null==e.userName?Object(b.jsx)("strong",{children:"Not Set"}):e.userName}),show_details:(e,t)=>Object(b.jsx)("td",{className:"py-2",children:Object(b.jsx)(c.d,{color:"primary",variant:"outline",shape:"square",size:"sm",onClick:()=>{re(t)},children:se.includes(t)?"Hide":"Show"})}),update:(s,n)=>Object(b.jsx)("div",{children:U.login.roles.includes("RESET_USER")?Object(b.jsx)("td",{className:"py-2",children:Object(b.jsx)(c.d,{color:"warning",variant:"outline",shape:"square",size:"sm",onClick:()=>{return J((n=s).id),h(!1),C(n.firstName),B(n.lastName),P(n.email),q(n.phoneNo),V(n.userRoleId),H(n.username),Q(""),ee(!0),Z(!1),void t(!e);var n},children:"Update"})}):""}),delete:(e,t)=>Object(b.jsx)("td",{className:"py-2",children:Object(b.jsxs)(c.n,{children:[Object(b.jsx)(c.q,{color:"secondary",size:"sm"}),Object(b.jsxs)(c.p,{children:[U.login.roles.includes("DELETE_USER")&&"Deleted"!==e.userStatus?Object(b.jsx)(c.o,{href:"#",onClick:()=>E(e,"Delete"),children:"Delete"}):null,U.login.roles.includes("SUSPEND_USER")&&"Suspended"!==e.userStatus?Object(b.jsx)(c.o,{href:"#",onClick:()=>E(e,"Suspend"),children:"Suspend"}):null,U.login.roles.includes("ACTIVATE_USER")&&"Active"!==e.userStatus?Object(b.jsx)(c.o,{href:"#",onClick:()=>E(e,"Reactivate"),children:"Activate"}):null]})]})}),details:(e,t)=>Object(b.jsx)(c.j,{show:se.includes(t),children:Object(b.jsx)(c.f,{children:Object(b.jsxs)("div",{className:"d-flex justify-content-start",children:[Object(b.jsxs)("div",{style:{marginRight:20,width:"40%"},children:[Object(b.jsx)("h4",{style:{marginBottom:4},children:Object(b.jsx)("strong",{children:e.firstName+" "+e.lastName})}),Object(b.jsx)("p",{children:Object(b.jsxs)("p",{className:"text-muted",children:["Registered On: ",new Date(parseFloat(e.registeredDate)).toLocaleString()]})})]}),2==e.statusId?Object(b.jsxs)("div",{style:{marginRight:20,width:"60%"},children:[Object(b.jsx)("h6",{style:{marginBottom:4},children:Object(b.jsx)("strong",{children:"User Activation"})}),Object(b.jsxs)("p",{children:["User has not been activated yet.",Object(b.jsx)(c.d,{variant:"outline",color:"warning",shape:"square",size:"sm",style:{marginLeft:10,marginRight:10},children:"Resend Activation number"})]}),Object(b.jsxs)("div",{style:{marginRight:20,width:"60%"},children:[Object(b.jsx)("h6",{style:{marginBottom:4},children:Object(b.jsx)("strong",{children:"Activation Number"})}),Object(b.jsx)("p",{children:e.activationNo})]})]}):1==e.statusId?Object(b.jsxs)("div",{style:{marginRight:20,width:"60%"},children:[Object(b.jsx)("h6",{style:{marginBottom:4},children:Object(b.jsx)("strong",{children:"User Activation"})}),Object(b.jsxs)("p",{children:["User account Activated.",U.login.roles.includes("VIEW_AND_MAINTAIN_USER_MANAGEMENT")?Object(b.jsx)(c.d,{variant:"outline",color:"danger",shape:"square",size:"sm",style:{marginLeft:10,marginRight:10,paddingLeft:10,paddingRight:10},onClick:()=>E(e,"Suspend"),children:" Suspend User"}):""]})]}):4==e.statusId?Object(b.jsxs)("div",{style:{marginRight:20,width:"60%"},children:[Object(b.jsx)("h6",{style:{marginBottom:4},children:Object(b.jsx)("strong",{children:"User Activation"})}),Object(b.jsxs)("p",{children:["User account Suspended.",U.login.roles.includes("VIEW_AND_MAINTAIN_USER_MANAGEMENT")?Object(b.jsx)(c.d,{variant:"outline",color:"success",shape:"square",size:"sm",style:{marginLeft:10,marginRight:10},onClick:()=>E(e,"Reactivate"),children:" Reactivate User"}):""]})]}):""]})})})}})})]})})}),Object(b.jsx)("div",{children:Object(b.jsxs)(l.a,{open:p,TransitionComponent:O,keepMounted:!0,"aria-labelledby":"alert-dialog-slide-title","aria-describedby":"alert-dialog-slide-description",children:[Object(b.jsx)(j.a,{id:"alert-dialog-slide-title",children:Object(b.jsx)("h5",{children:"Are You sure?"})}),Object(b.jsx)(d.a,{children:Object(b.jsx)(u.a,{id:"alert-dialog-slide-description",children:Object(b.jsxs)("div",{children:[Object(b.jsx)("h5",{style:{textAlign:"center"},children:Object(b.jsxs)("small",{children:["You are about to ",x," User ",Object(b.jsx)("strong",{children:N.firstName+" "+N.lastName+" "})," - User ",Object(b.jsx)("strong",{children:N.userRoleName})]})}),Object(b.jsx)("h6",{style:{textAlign:"center"},children:" Are You sure you want to proceed?"})]})})}),Object(b.jsxs)("div",{className:"d-flex justify-content-between",children:[Object(b.jsx)("button",{type:"button",className:"btn btn-outline-danger m-2 btn-sm",onClick:()=>{f(!1)},children:"Cancel"}),Object(b.jsx)("button",{className:"btn btn-outline-success m-2 btn-sm",type:"submit",onClick:e=>{var t;t=N.id,x.length>0&&("Delete"===x?R(t):"Suspend"===x?A(t):"Reactivate"===x&&w(t))},children:"Continue"})]})]})})]})}}}]);
//# sourceMappingURL=9.3e0b242c.chunk.js.map