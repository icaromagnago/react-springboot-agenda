import styled from 'styled-components';

export const FormContainer = styled.form`
  margin-top: 30px;
  display: flex;
  flex-direction: column;

  select {
    flex: 1;
    border: 1px solid #eee;
    padding: 10px 15px;
    border-radius: 4px;
    font-size: 15px;
    background: none; 
  }

  input {
    flex: 1;
    border: 1px solid #eee;
    padding: 10px 15px;
    border-radius: 4px;
    font-size: 15px;
    background: none; 
    margin-top: 20px;
  }

  input[type="date"]:before {
    content: attr(placeholder) !important;
    color: #aaa;
    margin-right: 0.5em;
  }
  
  input[type="date"]:focus:before,
  input[type="date"]:valid:before {
    content: "";
  }

  textarea {
    margin-top: 20px;
    border: 1px solid #eee;
    padding: 10px 15px;
    border-radius: 4px;
    font-size: 15px;
    background: none;
    min-height: 100px;
  }
`;

export const SubmitButton = styled.button.attrs((props) => ({
    type: 'submit',
  }))`
    background: #007bff;
    border: 0;
    padding: 10px 15px;
    border-radius: 4px;
    margin-top: 15px;
    width: 100px;
    color: #fff;
  
    display: flex;
    justify-content: center;
    align-items: center;
    align-self: flex-end;
  `;

  export const TableContainer = styled.div`
    margin-top: 20px;
  `;
  