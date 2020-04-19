import React, { Component } from "react";
import { Link } from "react-router-dom";
import { Redirect } from "react-router";
import {
  Button,
  Card,
  CardBody,
  CardGroup,
  Col,
  Container,
  Form,
  Input,
  InputGroup,
  InputGroupAddon,
  InputGroupText,
  Row,
} from "reactstrap";

class Login extends Component {
  state = {
    username: "",
  };
  handleLogin = () => {
    const { username } = this.state;
    if (username === "Admin") {
      this.props.history.push("/admin/dashboard");
    } else if (username === "UNO") {
      this.props.history.push("/uno/reliefRequests");
    }
  };
  render() {
    console.log(this.state);
    return (
      <div className="app flex-row align-items-center">
        <Container>
          <Row className="justify-content-center">
            <Col md="8">
              <CardGroup>
                <Card className="p-4">
                  <CardBody>
                    <Form>
                      <h1>লগইন</h1>
                      <p className="text-muted">
                        পূর্ববর্তী একাউন্টে লগইন করুন
                      </p>
                      <InputGroup className="mb-3">
                        <InputGroupAddon addonType="prepend">
                          <InputGroupText>
                            <i className="icon-user"></i>
                          </InputGroupText>
                        </InputGroupAddon>
                        <Input
                          onChange={(e) =>
                            this.setState({ username: e.target.value })
                          }
                          type="text"
                          placeholder="Username"
                          autoComplete="username"
                        />
                      </InputGroup>
                      <InputGroup className="mb-4">
                        <InputGroupAddon addonType="prepend">
                          <InputGroupText>
                            <i className="icon-lock"></i>
                          </InputGroupText>
                        </InputGroupAddon>
                        <Input
                          type="password"
                          placeholder="পাসওয়ার্ড"
                          autoComplete="current-password"
                        />
                      </InputGroup>
                      <Row>
                        <Col xs="6">
                          <Button
                            color="primary"
                            className="px-4"
                            onClick={this.handleLogin}
                          >
                            লগইন
                          </Button>
                        </Col>
                        <Col xs="6" className="text-right">
                          <Button color="link" className="px-0">
                            পাসওয়ার্ড ভুলে গেছেন ?
                          </Button>
                        </Col>
                      </Row>
                    </Form>
                  </CardBody>
                </Card>
                <Card
                  className="text-white bg-primary py-5 d-md-down-none"
                  style={{ width: "44%" }}
                >
                  <CardBody className="text-center">
                    <div>
                      <h2>নতুন অ্যাকাউন্ট নিবন্ধন</h2>
                      <p>
                        নতুন অ্যাকাউন্ট নিবন্ধনের জন্য নিম্নের বাটনে ক্লিক করুন
                      </p>
                      <Link to="/register">
                        <Button
                          color="primary"
                          className="mt-3"
                          active
                          tabIndex={-1}
                        >
                          নিবন্ধন!
                        </Button>
                      </Link>
                    </div>
                  </CardBody>
                </Card>
              </CardGroup>
            </Col>
          </Row>
        </Container>
      </div>
    );
  }
}

export default Login;
