import React from "react";
import { Button, TextField, Grid, Paper } from "@material-ui/core";

class AddTodo extends React.Component {
    constructor(props) {
        super(props);
        this.state = { item: {title: "" }};
        this.add = props.add; // props함수를 this.add에 연결
    }

    onInputChange = (e) => {
        const thisItem = this.state.item;
        thisItem.title = e.target.value;
        this.setState({ item: thisItem });
        console.log(thisItem);
    }

    // (1) 함수 작성
    onButtonClick = () => {
        this.add(this.state.item); // add 함수 사용
        this.setState({ item: {title: "" }});
    }

    render() {
        // (2) 함수 연결
        return(
            <Paper style={{ margin : 16, padding:16 }}>
                <Grid container>
                    <Grid xs={11} md={11} item style={{ paddingRight:16 }}>
                    <TextField
                        placeholder="Add Todo here"
                        fullWidth
                        onChange={this.onInputChange}
                        value={this.state.item.title}
                    />
                </Grid>
                <Grid xs ={1} md={1} item>
                    <Button
                    fullWidth
                    color="secondary"
                    variant="outlined"
                    onClick={this.onButtonClick}
                    >
                        +
                     </Button>
                    </Grid>
                </Grid>
            </Paper>
        );
    }
}

export default AddTodo;