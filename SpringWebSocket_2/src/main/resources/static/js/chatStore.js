const { defineStore } = Pinia
const useChatStore = defineStore('chat',{
	state:()=>({
		stompClient:null,
		userId:'',
		msg:'',
		receiver:'',
		message:[] // 서버에서 받는 값
	}),
	actions:{
		// 연결후에 데이터를 받는 브라우저를 지정
		connect(){
			const socket = new SockJS('/ws-chat')
			this.stompClient = Stomp.over(socket)
			this.stompClient.connect({},()=>{
				this.stompClient.subscribe('/queue/private/'+this.userId,(msg)=>{
					this.message.push(JSON.parse(msg.body))
				})
			})
			console.log(this.message)
		},
		send(){
			this.stompClient.send('/app/chat.private',{},JSON.stringify({
				sender:this.userId,
				receiver:this.receiver,
				message:this.msg 
				
			}))
		}
		
	}
})