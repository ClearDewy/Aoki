import{d as U,r as a,t as $,c as h,b as o,F as f,v as j,e as L,p as x,x as y,f as c,o as u,n as F,w as I,y as z,L as p,z as d,i as V,j as A,_ as D}from"./index-f12f2e9c.js";const E={class:"container"},M={style:{padding:"14px"}},R={class:"inline-span",style:{display:"block","text-align":"left","font-size":"var(--el-font-size-extra-large)",width:"100%"}},T={class:"inline-span",style:{display:"block","text-align":"left","font-size":"var(--el-font-size-small)",width:"100%"}},q={class:"demo-pagination-block",style:{padding:"1%",float:"right"}},G=U({__name:"Home",setup(J){const l=a(1),g=a([20,40,60]),i=a(g.value[0]),k=a(!1),b=a(!1),C=a(!1),w=e=>{r(),console.log(`${e} items per page`)},N=e=>{r(),console.log(`current page: ${e}`)},n=a([]),v=a([]),_=a(0),r=()=>{v.value=n.value.slice((l.value-1)*i.value,Math.min(n.value.length,l.value*i.value)),console.log(v.value.length)},m=()=>{x.getLessonList().then(e=>{e&&(n.value=e.data.reverse()),_.value=n.value.length,r()}).catch(e=>{y("获取课程列表失败")})};m(),$.on("refreshLessonList",m);const S=e=>{e.id&&x.getLesson(e.id).then(t=>{t&&(p.value=t.data,p.value.ownerName=e.ownerName,d.setItem("Lesson",p.value),d.remove("topic"),d.remove("task"),V.push(A.Lesson))}).catch(t=>{y("进入课程失败")})};return(e,t)=>{const B=c("el-image"),P=c("el-card"),H=c("el-pagination");return u(),h(f,null,[o("div",E,[(u(!0),h(f,null,j(n.value,s=>(u(),F(P,{"body-style":{padding:"0px"},style:{width:"240px",height:"206px"},shadow:"hover",onClick:K=>S(s)},{default:I(()=>[L(B,{src:s.avatarURL,fit:"fill",style:{height:"130px",width:"240px"}},null,8,["src"]),o("div",M,[o("span",R,z(s.name),1),o("span",T,z(s.ownerName),1)])]),_:2},1032,["onClick"]))),256))]),o("div",q,[L(H,{"current-page":l.value,"onUpdate:currentPage":t[0]||(t[0]=s=>l.value=s),"page-size":i.value,"onUpdate:pageSize":t[1]||(t[1]=s=>i.value=s),"page-sizes":g.value,small:k.value,disabled:b.value,background:C.value,layout:"total, sizes, prev, pager, next, jumper",total:_.value,onSizeChange:w,onCurrentChange:N},null,8,["current-page","page-size","page-sizes","small","disabled","background","total"])])],64)}}});const Q=D(G,[["__scopeId","data-v-0e85e119"]]);export{Q as default};
