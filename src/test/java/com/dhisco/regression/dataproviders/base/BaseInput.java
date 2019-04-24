package com.dhisco.regression.dataproviders.base;

import com.dhisco.regression.core.BasePojo;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Properties;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 24-04-2019
 */
@Getter @Setter
public abstract class BaseInput extends BasePojo {

	private String dataFile;
}
