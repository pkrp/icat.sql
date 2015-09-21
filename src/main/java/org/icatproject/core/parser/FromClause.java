package org.icatproject.core.parser;

import java.util.Map;

import org.apache.log4j.Logger;
import org.icatproject.core.IcatException;
import org.icatproject.core.entity.EntityBaseBean;
import org.icatproject.core.manager.EntityInfoHandler;

public class FromClause {
	private static Logger logger = Logger.getLogger(FromClause.class);

	private String string;
	private Class<? extends EntityBaseBean> bean;

	private enum State {
		LEFT, LJOIN, JOIN, NONE
	}

	private State state;

	public FromClause(Input input, String idVar, Map<String, Integer> idVarMap, boolean isQuery)
			throws ParserException, IcatException {
		StringBuilder sb = new StringBuilder();
		input.consume(Token.Type.FROM);
		Token t = input.peek(0);
		state = State.NONE;
		while (t != null && t.getType() != Token.Type.WHERE && t.getType() != Token.Type.GROUP
				&& t.getType() != Token.Type.HAVING && t.getType() != Token.Type.ORDER
				&& t.getType() != Token.Type.INCLUDE && t.getType() != Token.Type.LIMIT) {
			t = input.consume();
			if (t.getType() == Token.Type.NAME) {
				String val = t.getValue();
				logger.info(val);
				if (EntityInfoHandler.getAlphabeticEntityNames().contains(val)) {
					logger.info("contains");
					sb.append(" " + val);
				} else {
					if (!isQuery && state == State.NONE) {
						logger.info("if");
						sb = new StringBuilder();
					} else {
						logger.info("looking for a dott");
						int dot = val.indexOf('.');
						String idv = dot < 0 ? val.toUpperCase() : val.substring(0, dot)
								.toUpperCase();
						Integer intVal = idVarMap.get(idv);
						if (intVal == null) {
							intVal = idVarMap.size();
							idVarMap.put(idv, intVal);
						}
						sb.append(" $" + intVal + "$");
						if (dot >= 0) {
							sb.append(val.substring(dot));
						}
					}
				}
			} else if (t.getType() == Token.Type.FETCH || t.getType() == Token.Type.INNER
					|| t.getType() == Token.Type.OUTER || t.getType() == Token.Type.FETCH) {
				// Skip token
			} else {
				if (t.getType() == Token.Type.LEFT) {
					state = State.LEFT;
				} else if (t.getType() == Token.Type.JOIN) {
					if (state == State.LEFT || !isQuery) {
						state = State.LJOIN;
						sb.append(" LEFT JOIN");
					} else {
						state = State.JOIN;
						sb.append(" JOIN");
					}
				} else {
					logger.info("elseeee");
					sb.append(" " + t.getValue());
				}
			}
			Token next = input.peek(0);
			if (next != null && next.getType() == Token.Type.AS) {
				input.consume(Token.Type.AS);
				next = input.peek(0);
			}
			if (next != null && next.getType() == Token.Type.NAME
					&& next.getValue().toUpperCase().equals(idVar)) {
				if (bean != null) {
					throw new ParserException("Attempt to define " + idVar
							+ " more than once in FROM clause");
				}
				logger.info("got bean");
				logger.info(t.getValue());
				bean = EntityInfoHandler.getClass(t.getValue());
			}
			t = next;
		}
		if (bean == null) {
			throw new ParserException(idVar + " is not defined in the FROM clause");
		}
		string = sb.toString();
	}

	@Override
	public String toString() {
		return string;
	}

	public Class<? extends EntityBaseBean> getBean() {
		return bean;
	}

}
