import React, { Component, lazy } from "react";

import FormItem from "./formItem";
import {
  Button,
  Card,
  CardBody,
  CardHeader,
  Col,
  FormGroup,
  Input,
  Label,
  Row,
} from "reactstrap";

//Random Numbers
function random(min, max) {
  return Math.floor(Math.random() * (max - min + 1) + min);
}

var elements = 27;
var data1 = [];
var data2 = [];
var data3 = [];
var id = 1;

for (var i = 0; i <= elements; i++) {
  data1.push(random(50, 200));
  data2.push(random(80, 100));
  data3.push(65);
}

class Dashboard extends Component {
  constructor(props) {
    super(props);

    this.toggle = this.toggle.bind(this);
    this.onRadioBtnClick = this.onRadioBtnClick.bind(this);

    this.state = {
      dropdownOpen: false,
      radioSelected: 2,
      itemArray: [],
    };
  }

  toggle() {
    this.setState({
      dropdownOpen: !this.state.dropdownOpen,
    });
  }

  onRadioBtnClick(radioSelected) {
    this.setState({
      radioSelected: radioSelected,
    });
  }

  loading = () => (
    <div className="animated fadeIn pt-1 text-center">Loading...</div>
  );

  handleApprove = (familyObj) => {
    let families = [...this.state.families];
    families = families.filter((family) => family.id !== familyObj.id);
    familyObj.approved = !familyObj.approved;
    families = [...families, familyObj];
    families = families.sort((a, b) => a.id - b.id);
    this.setState({ families });
  };

  handleAdd = () => {
    let itemArray = [...this.state.itemArray];
    itemArray = [...itemArray, { id: id++ }];
    this.setState({ itemArray });
  };

  render() {
    console.log(families);
    const { families, itemArray } = this.state;
    return (
      <div className="animated fadeIn">
        <Row>
          <Col xs="12" sm="8" className="mx-auto">
            <Card>
              <CardHeader>
                <strong>ত্রাণ ব্যবস্থাপনা</strong>
              </CardHeader>
              <CardBody>
                <Row>
                  <Col xs="12">
                    <FormGroup>
                      <Label htmlFor="name">নোট</Label>
                      <Input
                        type="text"
                        id="name"
                        placeholder="নোট প্রদান করুন"
                        required
                      />
                    </FormGroup>
                  </Col>
                </Row>
                <Row>
                  <Col xs="12"></Col>
                </Row>
                {itemArray.map((item) => (
                  <FormItem key={item.id} />
                ))}

                <Button onClick={this.handleAdd}>নতুন আইটেম যুক্ত করুন</Button>
                <FormGroup className="form-actions">
                  <Button
                    type="submit"
                    size="md"
                    color="success"
                    style={{ float: "right" }}
                  >
                    সাবমিট
                  </Button>
                </FormGroup>
              </CardBody>
            </Card>
          </Col>
        </Row>
      </div>
    );
  }
}

export default Dashboard;
