import{d as S,r as _,a as y,c as L,b as o,e,w as t,f as i,o as x,u as m,g as D,l as E,h as f,i as G,j as T,m as j,k as A,n as q,p as w,q as P,s as H,_ as J}from"./index-3165b167.js";const v=c=>(P("data-v-b7062949"),c=c(),H(),c),K={key:0,id:"login"},M={class:"login2 alignment-container"},O=v(()=>o("h2",{style:{"text-align":"left"}},"用户登录",-1)),W=v(()=>o("div",null,[o("p",{style:{color:"#9b9b9b"}},"     QQ群：1233445"),o("img",{width:"200px"}),o("p",{style:{color:"#9b9b9b"}},"        有疑问加群咨询")],-1)),X={key:1,id:"email-login"},Y={class:"email-login2 alignment-container"},Z=v(()=>o("h2",{style:{"text-align":"left"}},"用户登录",-1)),ee=v(()=>o("div",null,[o("p",{style:{color:"#9b9b9b"}},"        QQ群：1233445"),o("img",{width:"200px"}),o("p",{style:{color:"#9b9b9b"}},"        有疑问加群咨询")],-1)),te=S({__name:"Login",setup(c){const h=_(!1),r=y({password:"",username:""}),k=_(),R=y({username:[{required:!0,message:"请输入用户名",trigger:"blur"}],password:[{required:!0,message:"请输入密码",trigger:"blur"}]}),a=y({code:"",email:""}),b=_(!1),V=_(Date.now()),C=_(),$=y({email:[{required:!0,message:"请输入邮箱",trigger:"blur"},{type:"email",message:"请输入正确的邮箱"}],code:[{required:!0,message:"请输入验证码",trigger:"blur"}]}),B=()=>{w.getVerifyCode(a.email).then(n=>{V.value=Date.now()+1e3*60,b.value=!0})},I=()=>{b.value=!1},Q=async n=>{n&&await n.validate((l,d)=>{l&&w.login(r)})},U=async n=>{n&&await n.validate((l,d)=>{l&&w.emailLogin(a)})};return(n,l)=>{const d=i("el-icon"),g=i("el-input"),p=i("el-form-item"),u=i("el-button"),z=i("el-form"),F=i("el-space"),N=i("el-countdown");return h.value?(x(),L("div",X,[o("div",Y,[e(F,{size:100},{default:t(()=>[o("div",null,[Z,e(z,{ref_key:"ruleFormRefE",ref:C,model:a,rules:$},{default:t(()=>[e(p,{prop:"email"},{default:t(()=>[e(g,{modelValue:a.email,"onUpdate:modelValue":l[5]||(l[5]=s=>a.email=s),style:{width:"280px",height:"40px","margin-top":"20px"},placeholder:"请输入邮箱"},{prefix:t(()=>[e(d,{style:{"font-size":"18px"}},{default:t(()=>[e(m(j))]),_:1})]),_:1},8,["modelValue"])]),_:1}),e(p,{prop:"code"},{default:t(()=>[e(g,{modelValue:a.code,"onUpdate:modelValue":l[6]||(l[6]=s=>a.code=s),style:{width:"280px",height:"40px","margin-top":"20px"},placeholder:"请输入验证码"},{prefix:t(()=>[e(d,{style:{"font-size":"18px"}},{default:t(()=>[e(m(A))]),_:1})]),_:1},8,["modelValue"]),b.value?(x(),q(N,{key:0,format:"ss",value:V.value,style:{position:"absolute",right:"10px",top:"24px"},"value-style":"color:#409eff;font-size: 14px",onFinish:I},null,8,["value"])):(x(),q(u,{key:1,style:{position:"absolute",right:"10px",top:"24px"},type:"text",onClick:B},{default:t(()=>[f("获取验证码 ")]),_:1}))]),_:1}),e(p,null,{default:t(()=>[e(u,{type:"primary",style:{width:"280px",height:"40px","margin-top":"20px"},onClick:l[7]||(l[7]=s=>U(C.value))},{default:t(()=>[f("登录")]),_:1})]),_:1})]),_:1},8,["model","rules"]),e(u,{onClick:l[8]||(l[8]=s=>h.value=!1),type:"primary",link:"",style:{float:"left"}},{default:t(()=>[f("返回")]),_:1})]),ee]),_:1})])])):(x(),L("div",K,[o("div",M,[e(F,{size:100},{default:t(()=>[o("div",null,[O,e(z,{ref_key:"ruleFormRef",ref:k,model:r,rules:R},{default:t(()=>[e(p,{prop:"username"},{default:t(()=>[e(g,{modelValue:r.username,"onUpdate:modelValue":l[0]||(l[0]=s=>r.username=s),style:{width:"280px",height:"40px","margin-top":"20px"},placeholder:"用户名"},{prefix:t(()=>[e(d,{style:{"font-size":"18px"}},{default:t(()=>[e(m(D))]),_:1})]),_:1},8,["modelValue"])]),_:1}),e(p,{prop:"password"},{default:t(()=>[e(g,{modelValue:r.password,"onUpdate:modelValue":l[1]||(l[1]=s=>r.password=s),style:{width:"280px",height:"40px","margin-top":"20px"},placeholder:"密码","show-password":""},{prefix:t(()=>[e(d,{style:{"font-size":"18px"}},{default:t(()=>[e(m(E))]),_:1})]),_:1},8,["modelValue"])]),_:1}),e(p,null,{default:t(()=>[e(u,{type:"primary",onClick:l[2]||(l[2]=s=>Q(k.value)),style:{width:"280px",height:"40px","margin-top":"20px"}},{default:t(()=>[f("登录 ")]),_:1})]),_:1})]),_:1},8,["model","rules"]),e(u,{type:"primary",link:"",style:{float:"left"},onClick:l[3]||(l[3]=s=>m(G).push(m(T).Register))},{default:t(()=>[f("新用户注册")]),_:1}),e(u,{type:"primary",link:"",style:{float:"right"},onClick:l[4]||(l[4]=s=>h.value=!0)},{default:t(()=>[f("邮箱验证码登录")]),_:1})]),W]),_:1})])]))}}});const oe=J(te,[["__scopeId","data-v-b7062949"]]);export{oe as default};
