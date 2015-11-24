package com.mcfly.pyl.sqlite.dao.extended.interfaces;

import com.mcfly.pyl.sqlite.dal.Contact;

public interface IContact{

  /**
   *
   * @param name
   * @return
   */
  public Contact getContact(String name);

  /**
   *
   * @param id (android id saved in db)
   * @return
   */
  public Contact getContact(int id);

  /**
   *
   * @param element
   * @return
   */
  public int save(Contact element);

}
