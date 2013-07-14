/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solxiom.signincontrol.service;

import com.solxiom.signincontrol.entity.Comment;
import java.util.Collection;

/**
 *
 * @author kavan
 */
public interface CommentService extends AbstractCrudService<Comment> {

	Collection<Comment> loadByText(String text);

}
