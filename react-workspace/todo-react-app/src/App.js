import React from 'react';
import Todo from './Todo';
import AddTodo from "./AddTodo.js";
import { Paper, List, Container } from "@material-ui/core";
import './App.css';

class App extends React.Component{
  constructor(props){
    super(props);
   
    this.state = {
      items : 
      [],
    };
  }

  //(1)함수 추가

  add = (item) => {
    const thisItems = this.state.items;
    item.id = "ID-" + thisItems.length // key를 위한 id 추가
    item.done = false; // done 초기화
    thisItems.push(item); //리스트에 아이템 추가
    this.setState({ items:thisItems}); // 업데이트는 반드시 this.setState로 해야됨
    console.log("items : ",this.state.items);
  }

  delete = (item) => {
    const thisItems = this.state.items;
    console.log("Before update Items : ", this.state.items)
    const newItems = thisItems.filter( e => e.id !== item.id);
    this.setState({ items: newItems }, () => {
      // 디버깅 콜백
      console.log("Update Items : ", this.state.items)
    });
  }

  render() {
   
   var todoItems = this.state.items.length > 0 && (
    <Paper style={{ margin: 16 }}>
      <List>
        {this.state.items.map((item, idx) => (
          <Todo item = {item} key = {item.id}  delete={this.delete} />
          
        ))}
      </List>
    </Paper>
   );

   // (2) 함수 연결
   return (
    <div className="App">
      <Container maxWidth ="md">
        <AddTodo add ={this.add} />
        <div className="TodoList">{todoItems}</div>
      </Container>
    </div>
   )
  }
}

export default App;
