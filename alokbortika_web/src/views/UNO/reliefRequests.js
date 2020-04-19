import React, { Component, lazy } from "react";
import { families } from "./dumymData";
import { Button, Card, Col, Row, Table } from "reactstrap";
import { CustomTooltips } from "@coreui/coreui-plugin-chartjs-custom-tooltips";
import { getStyle, hexToRgba } from "@coreui/coreui/dist/js/coreui-utilities";

// Main Chart

//Random Numbers
function random(min, max) {
  return Math.floor(Math.random() * (max - min + 1) + min);
}

var elements = 27;
var data1 = [];
var data2 = [];
var data3 = [];

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
      families: families,
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

  render() {
    console.log(families);
    const { families } = this.state;
    return (
      <div className="animated fadeIn">
        <Row>
          <Col>
            <Card>
              <Table
                hover
                responsive
                className="table-outline mb-0 d-none d-sm-table"
              >
                <thead className="thead-light">
                  <tr>
                    <th className="text-center">সর্বমোট সদস্যসংখ্যা</th>
                    <th className="text-center">পুরুষ</th>
                    <th className="text-center">মহিলা</th>
                    <th className="text-center">ঠিকানা</th>
                    <th className="text-center">পদক্ষেপ</th>
                  </tr>
                </thead>
                <tbody>
                  {families.map((family) => (
                    <tr>
                      <td className="text-center">{family.totalMembers}</td>
                      <td className="text-center">{family.male}</td>
                      <td className="text-center">{family.female}</td>
                      <td className="text-center">{family.address}</td>
                      <td className="text-center">
                        <Button
                          style={{ width: "150px" }}
                          className={family.approved ? "approved" : ""}
                          disabled={family.approved}
                          onClick={() => this.handleApprove(family)}
                        >
                          {" "}
                          {family.approved ? "অনুমোদিত" : "অনুমোদন করুন"}
                        </Button>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </Table>
            </Card>
          </Col>
        </Row>
      </div>
    );
  }
}

export default Dashboard;
