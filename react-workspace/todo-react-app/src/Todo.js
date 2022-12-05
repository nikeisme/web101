import React from 'react';
import { ListItem, 
        ListItemText,
         InputBase,
          Checkbox, 
          ListItemSecondaryAction,
          IconButton,
        } from "@material-ui/core";
import DeleteOutlined from "@material-ui/icons/DeleteOutlined";

class Todo extends React.Component {
    constructor(props){
        super(props); // porps 오브젝트 초기화
        this.state = {item: props.item, readOnly: true}; // state는 리액트가 관리하는 오브젝트
        this.delete = props.delete;
    }

    deleteEventHandler = () => {
        this.delete(this.state.item)
    }

    offReadOnlyMode = () => {
        console.log("Envent!", this.state.readOnly)
        this.setState({ readOnly: false}, () => {
            console.log("ReadOnly?", this.state.readOnly)
        });
    }

    enterKeyEventHandler = (e) => {
        if (e.key === "Enter") {
            this.setState({ readOnly: true});
        }
    };

    editEventHadler = (e) => {
        const thisItem = this.state.item;
        thisItem.title = e.target.value;
        this.setState({ item:thisItem });
    }

    checkboxEvnetHandler = (e) => {
        const thisItem = this.state.item;
        thisItem.done = !thisItem.done;
        this.setState({ item : thisItem });
    }

    render(){
        const item = this.state.item;
        return (
            <ListItem>
                <Checkbox checked={item.done} onChange={this.checkboxEvnetHandler} />
                <ListItemText>
                <InputBase
                    inputProps ={{ 
                        "aria-label" : "naked",
                        readOnly: this.state.readOnly,
                    }}
                    type = "text"
                    id = {item.id}
                    name = {item.id}
                    value = {item.title}
                    fullWidth={true}
                    onClick = {this.offReadOnlyMode}
                    onChange={this.editEventHadler}
                    onKeyPress={this.enterKeyEventHandler}
                    />
                </ListItemText>

                <ListItemSecondaryAction>
                    <IconButton
                     aria-label="Delete Todo"
                     onClick={this.deleteEventHandler}>
                    <DeleteOutlined />
                </IconButton>
                </ListItemSecondaryAction>
            </ListItem>
        );
    }
}

export default Todo;