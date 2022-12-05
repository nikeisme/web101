import React from 'react';
import Todo from './Todo';
import './App.css';

class App extends React.Component{
  constructor(props){
    super(props);
    // (1) item -> items 배열로
    this.state = {
      items : [
        {id:"0", title: "Hello World 1", done:true},
        {id:"1", title: "Hello World 2", done:false},
      ]
    };
  }
  render() {
   // (2) 자바스크립트가 제공하는 map 함수를 이용한 배열을 반복해 <Todo... /> 컴포넌트 생성
   var todoItems = this.state.items.map((item,idx) =>(
    <Todo item= {item} key={item.id} />
   ));

   //(3) 생성된 컴포넌트 리턴
   return <div className='App'>{todoItems}</div>
  }
}

export default App;
