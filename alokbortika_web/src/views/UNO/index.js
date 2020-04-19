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
                    {/* <th className="text-center">
                      <i className="icon-people"></i>
                    </th> */}
                    <th className="text-center"> Total Members</th>
                    <th className="text-center">Male</th>
                    <th className="text-center">Female</th>
                    <th className="text-center">Address</th>
                    <th className="text-center">Action</th>
                    {/* <th>Activity</th> */}
                  </tr>
                  {/* <tr>
                    <th className="text-center">
                      <i className="icon-people"></i>
                    </th>
                    <th>Total Members</th>
                    <th className="text-center">Male</th>
                    <th>Female</th>
                    <th className="text-center">Address</th>
                    <th>Activity</th>
                  </tr> */}
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
                          className={family.approved ? "approved" : ""}
                          disabled={family.approved}
                          onClick={() => this.handleApprove(family)}
                        >
                          {" "}
                          {family.approved ? "Approved" : "Approve"}
                        </Button>
                      </td>
                    </tr>
                  ))}
                  {/* <tr>
                    <td className="text-center">
                      <div className="avatar">
                        <img
                          src={"assets/img/avatars/1.jpg"}
                          className="img-avatar"
                          alt="admin@bootstrapmaster.com"
                        />
                        <span className="avatar-status badge-success"></span>
                      </div>
                    </td>
                    <td>
                      <div>Yiorgos Avraamu</div>
                      <div className="small text-muted">
                        <span>New</span> | Registered: Jan 1, 2015
                      </div>
                    </td>
                    <td className="text-center">
                      <i
                        className="flag-icon flag-icon-us h4 mb-0"
                        title="us"
                        id="us"
                      ></i>
                    </td>
                    <td>
                      <div className="clearfix">
                        <div className="float-left">
                          <strong>50%</strong>
                        </div>
                        <div className="float-right">
                          <small className="text-muted">
                            Jun 11, 2015 - Jul 10, 2015
                          </small>
                        </div>
                      </div>
                      <Progress
                        className="progress-xs"
                        color="success"
                        value="50"
                      />
                    </td>
                    <td className="text-center">
                      <i
                        className="fa fa-cc-mastercard"
                        style={{ fontSize: 24 + "px" }}
                      ></i>
                    </td>
                    <td>
                      <div className="small text-muted">Last login</div>
                      <strong>10 sec ago</strong>
                    </td>
                  </tr> */}
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
