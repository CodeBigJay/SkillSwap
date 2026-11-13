import{useEffect,useState}from'react';
import{Bell,Check,CheckCheck,Clock}from'lucide-react';
type Item={id:number;message:string;type:string;read:boolean;createdAt:string};
type Props={username:string;password:string};
const API=import.meta.env.VITE_API_URL||'http://localhost:8080';
async function api<T>(path:string,p:Props,init:RequestInit={}):Promise<T>{const r=await fetch(API+path,{...init,headers:{'Content-Type':'application/json',Authorization:'Basic '+btoa(p.username+':'+p.password),...init.headers}});if(!r.ok)throw new Error((await r.text())||'Request failed');return r.status===204?undefined as T:r.json();}
export default function Notifications(props:Props){
 const[items,setItems]=useState<Item[]>([]),[error,setError]=useState(''),[busy,setBusy]=useState(false);
 async function load(){setBusy(true);try{setItems(await api<Item[]>('/api/notifications',props));setError('');}catch(e){setError(e instanceof Error?e.message:'Could not load notifications');}finally{setBusy(false);}}
 useEffect(()=>{void load();},[]);
 async function markRead(id:number){try{const updated=await api<Item>('/api/notifications/'+id+'/read',props,{method:'PATCH'});setItems(current=>current.map(item=>item.id===id?updated:item));}catch(e){setError(e instanceof Error?e.message:'Could not update notification');}}
 const unread=items.filter(item=>!item.read).length;
 return <section className="panel"><div className="panel-head"><div><h2>Notifications</h2><p>Updates about your skill exchanges and account.</p></div><div className="notification-tools"><span className="tag">{unread} UNREAD</span><button className="refresh" onClick={load} disabled={busy}>Refresh</button></div></div>{error&&<p className="exchange-error">{error}</p>}{items.length?items.map(item=><article className={'notification '+(item.read?'read':'unread')} key={item.id}><div className="notification-icon"><Bell size={17}/></div><div className="notification-body"><strong>{item.message}</strong><small><Clock size={12}/>{new Date(item.createdAt).toLocaleString()}</small></div>{item.read?<span className="read-label"><CheckCheck size={15}/> Read</span>:<button className="mark-read" onClick={()=>markRead(item.id)}><Check size={14}/> Mark read</button>}</article>):<div className="empty"><Bell/><p>{busy?'Loading notifications…':'You’re all caught up. Exchange updates will appear here.'}</p></div>}</section>;
}