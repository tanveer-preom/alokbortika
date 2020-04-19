import React, { useState } from "react";
import {
  Button,
  Col,
  FormGroup,
  FormText,
  Input,
  InputGroup,
  InputGroupAddon,
  InputGroupButtonDropdown,
  InputGroupText,
  Label,
  Row,
} from "reactstrap";

const FormItem = (props) => {
  const [type, setType] = useState("RICE");
  const [amount, setAmount] = useState(0);
  const kgArray = ["RICE", "POTATO", "PULSE"];
  const ltrArray = ["OIL"];

  return (
    <Row>
      <Col xs="4">
        <FormGroup>
          <Label htmlFor="ccmonth">পণ্যের ধরন</Label>
          <Input
            type="select"
            name="ccmonth"
            id="ccmonth"
            onChange={(e) => setType(e.target.value)}
          >
            <option value="RICE">Rice</option>
            <option value="OIL">Oil</option>
            <option value="PULSE">Pulse</option>
            <option value="POTATO">Potato</option>
          </Input>
        </FormGroup>
      </Col>
      <Col xs="4">
        <FormGroup>
          <Label htmlFor="appendedInput">পরিমাণ</Label>
          <div className="controls">
            <InputGroup>
              <Input id="appendedInput" size="16" type="number" min={0} />
              <InputGroupAddon addonType="append">
                <InputGroupText>
                  {kgArray.indexOf(type) >= 0 ? "kg" : "ltr"}
                </InputGroupText>
              </InputGroupAddon>
            </InputGroup>
          </div>
        </FormGroup>
      </Col>
    </Row>
  );
};

export default FormItem;
