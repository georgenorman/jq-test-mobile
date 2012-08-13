package com.thruzero.domain.demo.dao.jpa;

import com.thruzero.domain.demo.dao.InputsDAO;
import com.thruzero.domain.demo.model.InputsModel;
import com.thruzero.domain.jpa.dao.JpaGenericDAO;

/**
 * A DAO for {@code InputsModel}, which uses JPA for storage.
 *
 * @author George Norman
 */
public class JpaInputsDAO extends JpaGenericDAO<InputsModel> implements InputsDAO {

  protected JpaInputsDAO() {
    super(InputsModel.class);
  }


}
