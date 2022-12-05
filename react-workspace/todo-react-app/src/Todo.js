import React from 'react';

class Todo extends React.Component {
    constructor(props){
        super(props); // porps 오브젝트 초기화
        this.state = {item: props.item}; // state는 리액트가 관리하는 오브젝트
    }
    render(){
        return (
            <div className='Todo'>
                <input type="checkbox"
                // 자바스크립트로 된 변수를 JSX에서 사용하기 위해 {}로 변수를 묶어줌
                // HTML안에서도 자바스크립트 사용 가능
                 id={this.state.item.id}
                name={this.state.item.id} 
                checked={this.state.item.done}/>
                <label id={this.state.item.id}>{this.state.item.title}</label>
            </div>
        );
    }
}

export default Todo;